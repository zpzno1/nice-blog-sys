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

import cn.kiwipeach.blog.domain.CommentReply;
import com.baomidou.mybatisplus.extension.service.IService;
import org.kiwipeach.blog.shiro.token.AccessToken;

/**
 * 评论 服务接口类
 *
 * @author kiwipeach
 * @create 2019-03-16
 */
public interface ICommentReplyService extends IService<CommentReply> {

    /**
     * 评论回复服务
     *
     * @param commentReply 评论和回复实体类型
     * @param accessToken  当前登录用户
     * @return
     */
    boolean createCommentReply(CommentReply commentReply, AccessToken accessToken);
}
