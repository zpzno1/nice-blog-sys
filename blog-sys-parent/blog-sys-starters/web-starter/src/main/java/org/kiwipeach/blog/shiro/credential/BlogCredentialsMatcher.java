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
package org.kiwipeach.blog.shiro.credential;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;
import org.kiwipeach.blog.shiro.token.AccessToken;

/**
 * 自定义博客用户账号密码比对器
 *
 * @author kiwipeach
 * @create 2019-01-21
 */
public class BlogCredentialsMatcher implements CredentialsMatcher {

    /**
     * 密码比对
     * @param token 前台或者三方token
     * @param info 数据库中的密码密文信息
     * @return 返回比对结果
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //TODO 三方账号登陆的时候，不需要经过密码比对器，只有在本站注册的账号才需要获取账号密文进行比对。
        AccessToken accessToken = (AccessToken) token;
        String platform = accessToken.getPlatform();
        //三方登陆，认证授权即可
        if ("qq".equals(platform) || "qq".equals(platform) || "qq".equals(platform)) {
            return true;
        } else {
            //本站用户，需要比对账号密码,假设现在使用明文密码比对方式
            String dbPassword = String.valueOf(info.getCredentials());
            return dbPassword.equals(accessToken.getPassword());
        }
    }
}
