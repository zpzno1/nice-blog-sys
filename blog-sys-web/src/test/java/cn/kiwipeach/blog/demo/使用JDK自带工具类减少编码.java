/*
 * Copyright 2019 liuburu@qq.com.
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
package cn.kiwipeach.blog.demo;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.BlogTag;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Objects;

/**
 * 描述：测试jdk java.util.Objects工具类
 *
 * @author kiwipeach
 * @create 2019-05-12
 */
@Slf4j
public class 使用JDK自带工具类减少编码 {

    @Test
    public void 基础使用() {
        Blog blog1 = new Blog();
        blog1.setTitle("新闻标题");
        Blog blog2 = new Blog();
        blog2.setTitle("新闻标题");
        log.info("Objects.equals(blog1, blog2)==>{}", Objects.equals(blog1, blog2));
        log.info("blog1.equals(blog2)==>{}", blog1.equals(blog2));
        log.info("abc.equals(abc)==>{}", "abc".equals("abc"));
        log.info("Objects.deepEquals(blog1, blog2)==>{}", Objects.deepEquals(blog1, blog2));
        log.info("Objects.hash(1,2,3)==>{}", Objects.hash(1, 2, 3, 4, 5, 6, 7, 8, 9, 0));
        log.info("Objects.hash(1,2,3)==>{}", Objects.hash(1, 2, 3, 4, 5, 6, 7, 9, 8, 0));
        log.info("Objects.hash(1,2,3)==>{}", Objects.hash(1, 2, 3, 4, 5));
    }

    @Test
    public void 实战好用() {
        String bar = null;
        String baz = null;
        if (Objects.isNull(bar)) {
            log.info("bar为空");
        }
        if (Objects.nonNull(baz)) {
            log.info("baz不为空");
        }
        String name = Objects.requireNonNull(bar, "bar must not be null");
        String age = Objects.requireNonNull(baz, "baz must not be null");
    }

}
