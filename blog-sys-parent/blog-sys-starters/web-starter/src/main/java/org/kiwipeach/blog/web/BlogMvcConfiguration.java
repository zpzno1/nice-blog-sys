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
package org.kiwipeach.blog.web;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;
import java.util.HashMap;
import java.util.Map;

/**
 * springmvc拓展类,拓展拦截器，过滤器，监听器等等组件。
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/28
 */
@Configuration
public class BlogMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // 设置默认欢迎页面
        registry.addViewController("/").setViewName("forward:/blog/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 注册blogDispatcherServlet，前端控制器
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean blogDispatcherServlet(DispatcherServlet dispatcherServlet, MultipartConfigElement multipartConfigElement) {
        ServletRegistrationBean bean = new ServletRegistrationBean(dispatcherServlet);
        bean.addUrlMappings("*.html");
        bean.addUrlMappings("*.json");
        bean.setLoadOnStartup(1);
        //TODO 注册多个前端控制器，暂时未找到使用场景。。。
        //bean.setMultipartConfig(multipartConfigElement);
        return bean;
    }
}
