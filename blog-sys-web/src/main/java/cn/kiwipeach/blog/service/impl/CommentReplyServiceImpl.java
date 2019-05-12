/*
 * Copyright 2019 kiwipeach[1099501218@qq.com].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.CommentReply;
import cn.kiwipeach.blog.domain.vo.BlogCommentVO;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.CommentReplyMapper;
import cn.kiwipeach.blog.param.CommentReplyParam;
import cn.kiwipeach.blog.service.ICommentReplyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 评论 服务实现类
 *
 * @author kiwipeach
 * @create 2019-03-16
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements ICommentReplyService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommentReplyMapper commentReplyMapper;
    @Autowired
    private ValueOperations<String, Object> valueOperations;


    @Override
    public AjaxResponse<Boolean> createBlogComment(CommentReplyParam commentReply, AccessToken accessToken) {
        // 0) 查找当前登录用户信息
        AccessToken currentUser = UserUtil.getCurrentUser();
        commentReply.setActiveUserId(currentUser.getId());
        // 1) 插入评论内容
        commentReply.setType("B_BLOG_COMMENT");
        if (commentReplyMapper.insert(commentReply) == 0) {
            throw new BlogException("-COMMENT-002", "博客评论插入失败！");
        }
        // 2）更新博客统计字段
        if (updateBlogCommentCount(commentReply.getParentId()) == false) {
            throw new BlogException("-COMMENT-002", "博客评论统计更新失败！");
        }
        return AjaxResponse.success(true);
    }

    @Override
    public AjaxResponse<Boolean> createCommentReply(CommentReplyParam commentReply, AccessToken accessToken) {
        // 1)插入回复内容
        commentReply.setType("B_COMMENT_REPLY");
        if (commentReplyMapper.insert(commentReply) == 0) {
            throw new BlogException("-COMMENT-002", "评论回复内容插入失败！");
        }
        // 2)维护博客评论回复统计字段
        if (updateBlogCommentCount(commentReply.getBlogId()) == false) {
            throw new BlogException("-COMMENT-002", "博客评论统计更新失败！");
        }
        // 3)维护评论回复统计字段
        CommentReply curCommentReply = commentReplyMapper.selectById(commentReply.getParentId());
        curCommentReply.setReplyCount(curCommentReply.getReplyCount() + 1);
        if (commentReplyMapper.updateById(curCommentReply) == 0) {
            throw new BlogException("-COMMENT-002", "博客评论回复统计失败！");
        }
        return AjaxResponse.success(true);
    }


    @Override
    public AjaxResponse<IPage<BlogCommentVO>> queryCommentByPage(IPage<BlogCommentVO> page, String parentId, String type) {
        page.setRecords(commentReplyMapper.selectCommenByPage(page, parentId, type));
        return AjaxResponse.success(page);
    }

    @Override
    public AjaxResponse<Boolean> modifyCommentReplyStarCount(String commentId) {
        //如果缓存中存在点赞数据，则不允许重复点赞
        String cacheKey = new StringBuffer("BLOG_COMMENT_REPLY_COUNT::comment_id_" + commentId).toString();
        if (Objects.isNull(valueOperations.get(cacheKey))) {
            Integer integer = commentReplyMapper.updateCommentStarCount(commentId);
            if (integer > 0) {
                valueOperations.set(cacheKey, 1, 2, TimeUnit.MINUTES);
                return AjaxResponse.success(true);
            } else {
                return AjaxResponse.fail("-BLOG_STAR_001", "评论点赞失败");
            }
        } else {
            return AjaxResponse.fail("-BLOG_STAR_001", "您近期点过赞");
        }
    }


    /**
     * 更新博客统计字段
     *
     * @param blogId 博客编号
     * @return 返回更新成功
     */
    private boolean updateBlogCommentCount(String blogId) {
        return blogMapper.updateCommentCountByBlogId(blogId) > 0 ? true : false;
    }

    /**
     * 更新博客回复统计字段
     *
     * @param commentReply
     * @return 返回更新成功
     */
    private boolean updateCommentReplyCount(CommentReply commentReply) {
        commentReply.setReplyCount(commentReply.getReplyCount() + 1);
        return commentReplyMapper.updateById(commentReply) > 0 ? true : false;
    }


}
