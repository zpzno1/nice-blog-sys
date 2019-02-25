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
package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 博客服务测试
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
@Slf4j
public class IBlogServiceTest extends BlogWebApplicationTests {

    @Autowired
    private IBlogService iBlogService;

    @Test
    public void insert() {
        //Blog blog = new Blog();
        //boolean isCreate = iBlogService.createBlog(blog);
        //log.info("博客是否创建成功？{}", isCreate);
    }
}
