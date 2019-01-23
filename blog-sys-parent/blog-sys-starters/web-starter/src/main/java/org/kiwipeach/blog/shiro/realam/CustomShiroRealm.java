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
package org.kiwipeach.blog.shiro.realam;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.kiwipeach.blog.shiro.token.AccessToken;

/**
 * 博客自定义认证授权Realm
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-07-29
 */
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm {

    public CustomShiroRealm(CredentialsMatcher credentialsMatcher) {
        //需要配置token的类型，否则会报错
        setAuthenticationTokenClass(AccessToken.class);
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 代认证的token
     * @return 返回带有token和密文的认证信息（密码比对就是比对该token中的用户密码和密文是否匹配）
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        AccessToken accessToken = (AccessToken) authenticationToken;
        //TODO 从数据库中查找到了一条用户
        AccessToken dbAccessToken = new AccessToken("AccessToken-JDLFJDLJF", "6FF96B97CF726B2D1DD31798135782FA", "kiwipeach", "kiwiipeach.png", "unknow");
        dbAccessToken.setPassword("this_is_password");
        if (!dbAccessToken.getId().equals("6FF96B97CF726B2D1DD31798135782FA")) {//数据库里面找不到用户openid
            throw new UnknownAccountException("用户不存在!");
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accessToken, dbAccessToken.getPassword(), "CustomShiroRealm");
        return authenticationInfo;
    }

    /**
     * 用户授权（//TODO 猜想：在有权限注解或者标签的时候会去执行该方法，判断当前用户是否具有访问权限）
     * 1、subject.hasRole(“admin”)
     * 2、@RequiresRoles("admin")
     * 3、在页面上加shiro标签的时候
     * @param principalCollection 认证对象
     * @return 返回权限集合
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取认证对象
        //SecUser secUser = (SecUser) principalCollection.getPrimaryPrincipal();
        //2.加载该认证对象的角色信息以及该用户的权限信息
        //SysUser secUser;
        //String username = secUser.getUserName();
        //Set<String> roles = secUserService.queryRoles(username);
        //List<String> permissions = secUserService.queryPermissions(username);
        ////3.包装授权信息，并且进行返回
        //SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        //info.addStringPermissions(permissions);
        //return info;
        AccessToken accessToken = (AccessToken) principalCollection.getPrimaryPrincipal();
        //无权限抛出AuthorizationException异常，可以在全局异常处理中对该异常进行判断，跳转到未授权页面
        return null;
    }
}
