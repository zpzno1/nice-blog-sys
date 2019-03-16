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
import cn.kiwipeach.blog.enums.CodeEnum;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountLockedException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 博客自定义认证授权Realm
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-07-29
 */
@Slf4j
public class CustomShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper sysUserMapper;

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
        AccessToken targetToken = (AccessToken) authenticationToken;
        //TODO 从数据库中查找到了一条用户
        String platform = targetToken.getPlatform();
        AccessToken dbAccessToken = null;
        if (CodeEnum.SYSTEM.toString().equalsIgnoreCase(platform)) {
            Wrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().eq("USER_NAME", targetToken.getUserName()).eq("PLATFORM", platform);
            SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
            try {
                userExceptionHandle(sysUser);
            } catch (AccountLockedException e) {
                throw new BlogException("-lOGIN_001", e.getMessage());
            }
            dbAccessToken = changeSysUser2AccessToken(sysUser);
        } else {
            dbAccessToken = targetToken;
        }
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(dbAccessToken, dbAccessToken.getPassword(), "CustomShiroRealm");
        //密码比对器：org.kiwipeach.blog.shiro.credential.BlogCredentialsMatcher
        return authenticationInfo;
    }

    private void userExceptionHandle(SysUser sysUser) throws AccountLockedException {
        if (sysUser == null) {
            throw new UnknownAccountException("用户不存在!");
        } else {
            if ("1".equals(sysUser.getAccountLock())) {
                throw new AccountLockedException("锁定状态码信息（请联系管理员处理）：" + sysUser.getLockReason());
            }
        }
    }

    /**
     * 父类信息转换到子类信息
     *
     * @param sysUser 系统用户
     * @return 返回用户accessToken
     */
    private AccessToken changeSysUser2AccessToken(SysUser sysUser) {
        AccessToken accessToken = new AccessToken();
        accessToken.setPlatform("system");
        accessToken.setId(sysUser.getId());
        accessToken.setUserName(sysUser.getUserName());
        accessToken.setPassword(sysUser.getPassword());
        accessToken.setThirdUserId(sysUser.getThirdUserId());
        accessToken.setNickName(sysUser.getNickName());
        accessToken.setEmail(sysUser.getEmail());
        accessToken.setHeadUrl(sysUser.getHeadUrl());
        accessToken.setAccountLock(sysUser.getAccountLock());
        accessToken.setCreateTime(sysUser.getCreateTime());
        accessToken.setUpdateTime(sysUser.getUpdateTime());
        accessToken.setLockReason(sysUser.getLockReason());
        return accessToken;
    }

    /**
     * 用户授权（//TODO 猜想：在有权限注解或者标签的时候会去执行该方法，判断当前用户是否具有访问权限）
     * 1、subject.hasRole(“admin”)
     * 2、@RequiresRoles("admin")
     * 3、在页面上加shiro标签的时候
     *
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
        //准备角色权限信息
        Set<String> roles = new HashSet<String>();
        List<String> permissions = new ArrayList<String>();
        if (accessToken.getUserName().equals("kiwipeach")&&accessToken.getPlatform().equals("system")){
            roles.add("BlogAdmin");
            roles.add("BlogUser");
            //roles.add("user");
            //permissions.add("blog:page:aboutxxx");
            //分装当前用户的角色授权信息并返回
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        info.addStringPermissions(permissions);
        //3. 返回 SimpleAuthorizationInfo 对象.
        return info;
        //无权限抛出AuthorizationException异常，可以在全局异常处理中对该异常进行判断，跳转到未授权页面
    }


}
