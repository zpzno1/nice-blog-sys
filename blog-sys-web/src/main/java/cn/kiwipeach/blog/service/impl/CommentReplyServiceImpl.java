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
import cn.kiwipeach.blog.domain.vo.BlogCommentVO;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.CommentReplyMapper;
import cn.kiwipeach.blog.service.ICommentReplyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kiwipeach.blog.shiro.token.AccessToken;
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
    public boolean createCommentReply(CommentReply commentReply, AccessToken accessToken) {
        //1）若为评论类型，需要维护博客的统计字段（COMMENT_COUNT）; 若为回复类型，需要维护博客的统计字段（COMMENT_COUNT）和评论的统计字段（REPLY_COUNT）
        String commentType = commentReply.getType();
        switch (commentType) {
            case "BLOG_COMMENT":
                String blogId = commentReply.getParentId();
                Blog blog = blogMapper.selectById(blogId);
                commentReply.setActiveUserId(accessToken.getThirdUserId());
                commentReply.setPassiveUserId(blog.getUserId());
                //保存评论信息，更新博客评论统计
                return save(commentReply) && updateBlogCommentCount(commentReply.getParentId());
            case "LAM_REPLY":
                String passiveUserId = commentReply.getParentId();
                commentReply.setActiveUserId(accessToken.getThirdUserId());
                commentReply.setPassiveUserId(passiveUserId);
                //保存评论信息，更新博客评论统计，更新评论回复统计
                return save(commentReply) && updateBlogCommentCount(commentReply.getParentId()) && updateCommentReplyCount(commentReply);
            default:
                throw new BlogException("-SYS_001", "未知系统参数异常");
        }
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
        return blogMapper.updateCommentCount(blogId) > 0 ? true : false;
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
