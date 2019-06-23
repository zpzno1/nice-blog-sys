/*
 * Copyright 2018 kiwipeach.
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
package org.kiwipeach.blog.autoconfig;

import cn.kiwipeach.blog.mapper.BlogMapper;
import org.kiwipeach.blog.filter.xss.XssSqlFilter;
import org.kiwipeach.blog.interceptor.BlogViewCountInterceptor;
import org.kiwipeach.blog.resolver.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * springmvc拓展类,拓展拦截器，过滤器，监听器等等组件。
 * 在jdk8中可以不用继承该适配器了（WebMvcConfigurerAdapter），直接使用接口即可。
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/28
 */
@Configuration
public class BlogMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private ValueOperations valueOperations;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置默认欢迎页面
        //super.addViewControllers(registry);
        registry.addViewController("/").setViewName("forward:/blog/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    /**
     * 拦截器配置
     *
     * @param registry 注册中心
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        registry.addInterceptor(new BlogViewCountInterceptor(blogMapper,valueOperations))
                .addPathPatterns("/blog/detail/**");
    }

    /**
     * 参数解析器配置
     *
     * @param argumentResolvers 参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new CurrentUserMethodArgumentResolver());
    }

    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new XssSqlFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludeUrlPatterns", "/favicon.ico,/assets/*,/assets_admin/*");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


}
