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
import cn.kiwipeach.blog.service.ICommentReplyService;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @PostMapping("comment/create")
    @ResponseBody
    public AjaxResponse<Boolean> createBlogComment(CommentReply commentReply, @CurrentUser AccessToken accessToken) {
        return new AjaxResponse<>(iCommentReplyService.createCommentReply(commentReply, accessToken));
    }
}
