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
package cn.kiwipeach.blog.controller;


import cn.kiwipeach.blog.anno.CurrentUser;
import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.CommentReply;
import cn.kiwipeach.blog.param.CommentReplyParam;
import cn.kiwipeach.blog.service.ICommentReplyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 评论 前端控制器
 *
 * @author kiwipeach
 * @create 2019-03-16
 */
@Controller
@RequestMapping("/commentReply")
public class CommentReplyController {

    @Autowired
    private ICommentReplyService iCommentReplyService;


    /**
     * 发表博客评论
     *
     * @param commentReply 评论对象
     * @param accessToken  当前登录用户
     * @return 返回评论结果
     */
    @PostMapping("comment/create")
    @ResponseBody
    public AjaxResponse<Boolean> createBlogComment(
            CommentReplyParam commentReply,
            @CurrentUser AccessToken accessToken) {
        return new AjaxResponse<>(iCommentReplyService.createBlogComment(commentReply, accessToken));
    }


    /**
     * 博客评论回复
     *
     * @param commentReply 评论对象
     * @param accessToken  当前登录用户
     * @return
     */
    @PostMapping("reply/create")
    @ResponseBody
    public AjaxResponse<Boolean> createCommentReply(
            CommentReplyParam commentReply,
            @CurrentUser AccessToken accessToken) {
        return new AjaxResponse<>(iCommentReplyService.createCommentReply(commentReply, accessToken));
    }


    /**
     * 评论回复分页查询接口
     *
     * @param page      分页入参
     * @param queryType 查询类型,[BLOG_COMMENT,BLOG_REPLY,LAM_COMMENT,LAM_REPLY]
     * @param parentId  父节点编号
     * @return 返回分页结果
     */
    @GetMapping("query")
    @ResponseBody
    public AjaxResponse<IPage<CommentReply>> pageQuery(Page page,
                                                       @RequestParam(required = true, value = "queryType") String queryType,
                                                       @RequestParam(required = true, value = "parentId") String parentId) {
        IPage iPage = iCommentReplyService.queryCommentByPage(page, parentId, queryType);
        return AjaxResponse.success(iPage);
    }


}
