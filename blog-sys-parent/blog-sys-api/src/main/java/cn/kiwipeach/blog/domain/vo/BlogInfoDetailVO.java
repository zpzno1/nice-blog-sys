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

import cn.kiwipeach.blog.domain.Blog;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 博客信息实体
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
@Getter
@Setter
public class BlogInfoDetailVO extends BlogInfoVO implements Serializable {
    /**
     * 博客内容
     */
    private String content;
    /**
     * 上一篇博客
     */
    private Blog previousBlog;
    /**
     * 下一篇博客
     */
    private Blog nextBlog;
}
