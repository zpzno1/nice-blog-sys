/*
 * Copyright 2019 kiwipeach(1099501218@qq.com).
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
package cn.kiwipeach.blog.domain.vo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 博客评论实体类
 *
 * @author kiwipeach
 * @create 2019-03-19
 */
@Getter
@Setter
public class BlogCommentVO implements Serializable {
    private static final long serialVersionUID = -3549664083005715895L;
    private String id;
    private String type;
    private String parentId;
    private String activeUserId;
    private String activeUserHeadUrl;
    private String passiveUserId;
    private String passiveUserHeadUrl;
    private String content;
    private Integer deleted;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String updateTime;
    private Integer starCount;
    private Integer replyCount;
    private String activeNickName;
    private String passiveNickName;

}
