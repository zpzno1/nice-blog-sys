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
package cn.kiwipeach.blog.configuration.shiro.factory;

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
     * @return
     */
    public Map<String,String> loadResourceAccessRules(){
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "logout");
        filterChainDefinitionMap.put("/login/success", "authc");//必须授权
        filterChainDefinitionMap.put("/**", "user");//认证过或“记住我”都可访问,例如helo或remember页面
        return filterChainDefinitionMap;
    }
}