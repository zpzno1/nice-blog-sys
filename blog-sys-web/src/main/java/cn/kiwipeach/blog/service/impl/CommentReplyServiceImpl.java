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

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.CommentReply;
import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.domain.vo.BlogCommentVO;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.CommentReplyMapper;
import cn.kiwipeach.blog.mapper.SysUserMapper;
import cn.kiwipeach.blog.param.CommentReplyParam;
import cn.kiwipeach.blog.service.ICommentReplyService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public boolean createBlogComment(CommentReplyParam commentReply, AccessToken accessToken) {
        // 0) 查找当前登录用户信息
        AccessToken currentUser = UserUtil.getCurrentUser();
        commentReply.setActiveUserId(currentUser.getId());
        // 1) 插入评论内容
        commentReply.setType("B_BLOG_COMMENT");
        if (commentReplyMapper.insert(commentReply) == 0) {
            throw new BlogException("-COMMENT-002", "评论插入失败！");
        }
        // 2）更新博客统计字段
        if (updateBlogCommentCount(commentReply.getParentId()) == false) {
            throw new BlogException("-COMMENT-002", "博客评论统计更新失败！");
        }
        return true;
    }

    @Override
    public boolean createCommentReply(CommentReplyParam commentReply, AccessToken accessToken) {
        // 1)插入回复内容
        commentReply.setType("B_COMMENT_REPLY");
        if (commentReplyMapper.insert(commentReply) == 0) {
            throw new BlogException("-COMMENT-002", "回复内容插入失败！");
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
        return true;
    }

    @Override
    public IPage<BlogCommentVO> queryCommentByPage(IPage<BlogCommentVO> page, String parentId, String type) {
        List<BlogCommentVO> commentReplies = commentReplyMapper.selectCommenByPage(page, parentId, type);
        return page.setRecords(commentReplies);
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
