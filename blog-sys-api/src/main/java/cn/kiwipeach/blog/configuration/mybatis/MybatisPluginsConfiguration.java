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
package cn.kiwipeach.blog.configuration.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis插件相关配置
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/09
 */
@Configuration
@MapperScan(value = {"cn.kiwipeach.blog.mapper"})
public class MybatisPluginsConfiguration {

    /**
     * 分页插件,mybaits-plus官网提供
     * @return paginationInterceptor
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
     return new PaginationInterceptor();
    }




}
