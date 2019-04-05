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
package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.CommentReply;
import cn.kiwipeach.blog.domain.vo.BlogCommentVO;
import cn.kiwipeach.blog.param.CommentReplyParam;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.cache.annotation.Cacheable;

/**
 * 评论 服务接口类
 *
 * @author kiwipeach
 * @create 2019-03-16
 */
public interface ICommentReplyService extends IService<CommentReply> {

    /**
     * 发表博客评论
     *
     * @param commentReply 评论对象
     * @param accessToken  当前登录用户
     * @return 返回评论结果
     */
    boolean createBlogComment(CommentReplyParam commentReply, AccessToken accessToken);


    /**
     * 博客评论回复
     *
     * @param commentReply 评论对象
     * @param accessToken  当前登录用户
     * @return
     */
    boolean createCommentReply(CommentReplyParam commentReply, AccessToken accessToken);


    /**
     * 分页查询博客评论信息
     *
     * @param page     分页入参
     * @param parentId 博客编号
     * @param type     查询类型
     * @return 返回博客评论分页信息
     */
    //@Cacheable(value = {"BLOG_COMMENT_REPLY"}, key = "'page_'+#type+'_'+#parentId+'_'+#page.current+'_'+#page.size")
    IPage<BlogCommentVO> queryCommentByPage(IPage<BlogCommentVO> page, String parentId, String type);

    /**
     * 修改博客评论回复的点赞数
     *
     * @param commentId 回复id
     * @return 返回更新结果
     */
    AjaxResponse<Boolean> modifyCommentReplyStarCount(String commentId);
}
