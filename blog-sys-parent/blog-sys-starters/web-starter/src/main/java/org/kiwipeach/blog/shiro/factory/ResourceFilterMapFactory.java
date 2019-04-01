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
package org.kiwipeach.blog.shiro.factory;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义规则加载器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/05
 */
public class ResourceFilterMapFactory {
    /**
     * user:记住我 anon:匿名 authc:认证
     * 第一次匹配优先规则
     * /shrio/login = anon
     * /shrio/logout = logout
     * /login.jsp = anon
     * /login/success= user
     * /user/index= user
     * /** = authc
     *
     * @return
     */
    public Map<String, String> loadResourceAccessRules() {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //1)配置匿名可以访问的路径
        // 登陆路径
        filterChainDefinitionMap.put("/unauthorize", "anon");
        filterChainDefinitionMap.put("/**/login", "anon");
        filterChainDefinitionMap.put("/**/oauth2.0/callback", "anon");

        //通用控制器
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/blog/index.html", "anon");
        filterChainDefinitionMap.put("/blog/detail.html", "anon");
        filterChainDefinitionMap.put("/blog/archive.html", "anon");
        //filterChainDefinitionMap.put("/blog/about.html", "roles[BlogUser]");

        filterChainDefinitionMap.put("/user", "anon");

        //duird
        filterChainDefinitionMap.put("/druid/**", "anon");


        // 静态资源路径
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");

        // 案例路径
        filterChainDefinitionMap.put("/demo/**", "anon");


        //2)配置需要拦截的路径
        filterChainDefinitionMap.put("/user/logout", "logout");
        filterChainDefinitionMap.put("/login/success", "authc");//必须认证和授权，记住我无效
        filterChainDefinitionMap.put("/commentReply/query", "anon");
        filterChainDefinitionMap.put("/commentReply/comment/create", "user");//评论回复，只需要记住我即可
        filterChainDefinitionMap.put("/**", "anon");//认证过或“记住我”都可访问,例如helo或remember页面
        return filterChainDefinitionMap;
    }
}
