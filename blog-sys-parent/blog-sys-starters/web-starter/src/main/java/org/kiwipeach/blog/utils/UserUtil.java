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
package org.kiwipeach.blog.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.kiwipeach.blog.shiro.token.AccessToken;

/**
 * 获取当前用户信息工具类
 *
 * @author kiwipeach
 * @create 2019-03-10
 */
public class UserUtil {

    /**
     * 获取当前用户信息
     *
     * @return
     */
    public static AccessToken getCurrentUser() {
        Object principal = SecurityUtils.getSubject().getPrincipals();
        if (principal instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection simplePrincipalCollection = (SimplePrincipalCollection) principal;
            AccessToken accessToken = (AccessToken) simplePrincipalCollection.getPrimaryPrincipal();
            return accessToken;
        } else {
            return null;
        }
    }
}
