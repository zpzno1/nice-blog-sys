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
package org.kiwipeach.blog.interceptor;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.mapper.BlogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 描述：博客详情查看浏览量拦截器
 *
 * @author kiwipeach
 * @create 2019-04-04
 */
@Slf4j
public class BlogViewCountInterceptor implements HandlerInterceptor {

    private BlogMapper blogMapper;
    private ValueOperations<String,Object> valueOperations;

    public BlogViewCountInterceptor(BlogMapper blogMapper, ValueOperations valueOperations) {
        this.blogMapper = blogMapper;
        this.valueOperations = valueOperations;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        String[] requstStrs = requestURI.split("/");
        String blogId = requstStrs[3];
        String cacheKey = new StringBuffer("BLOG_VIEW_COUNT:").append(blogId).append("_").append(request.getSession().getId()).toString();
        Object cacheViewCount = valueOperations.get(cacheKey);
        if (Objects.isNull(cacheViewCount)) {
            //1分钟缓存
            valueOperations.set(cacheKey, 1, 1, TimeUnit.MINUTES);
            Integer integer = blogMapper.updateBlogViewCountByBlogId(blogId);
            log.info("博客(id:{})浏览量:+{}", blogId, integer);
        }
        return true;
    }
}
