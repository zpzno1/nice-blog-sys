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

import cn.kiwipeach.blog.domain.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * 博客 前端控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-07-29
 */
public class CustomShiroRealm extends AuthorizingRealm {

    public CustomShiroRealm(CredentialsMatcher credentialsMatcher){
        super.setCredentialsMatcher(credentialsMatcher);
    }

    /**
     * 用户认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1. 把 AuthenticationToken 转换为 UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //2. 从 UsernamePasswordToken 中来获取 username
        String username = usernamePasswordToken.getUsername();
        //3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
        //credentical为数据库中的SHA1加密密码
        //SecUser secUser = secUserService.queryByUserName(username);
        //Object credentials = secUser.getPassword();
        //4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
        //测试数据
        SysUser secUser = new SysUser();
        secUser.setUserName("kiwipeach");
        secUser.setPassword("3a322406a9067b292325e13989c404dd1bf38ebf");
        if(secUser==null){
            throw new UnknownAccountException("用户不存在!");
        }
        String realName = getName();
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
        //1). secUser (principal): 认证的实体信息，目的是为了在授权的时候获取到当前的认知实体信息.
        //2). secUser.getPassword (credentials): 从数据库中获取的数据库密码信息.
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(secUser,secUser.getPassword(),credentialsSalt,realName);
        return authenticationInfo;
    }

    /**
     * 用户授权
     * @param principalCollection
     * @return
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
        return null;
    }
}
