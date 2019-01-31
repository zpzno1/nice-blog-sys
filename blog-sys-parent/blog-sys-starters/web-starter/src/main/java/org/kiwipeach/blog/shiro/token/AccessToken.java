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
package org.kiwipeach.blog.shiro.token;

import cn.kiwipeach.blog.domain.SysUser;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 博客登陆访问token
 *
 * @author kiwipeach
 * @create 2019-01-21
 */
public class AccessToken extends SysUser implements HostAuthenticationToken, RememberMeAuthenticationToken {

    /**
     * 当前登陆账号的token字符串,一定存在
     */
    private String tokenStr;
    /**
     * 失效时间，不一定有
     */
    private String expiresTime;
    /**
     * 刷新token，不一定有
     */
    private String refreshToken;
    /**
     * 记住我，不一定
     */
    private boolean rememberMe;
    /**
     * 当前登陆的三方平台[qq.github.gitee]
     */
    private String host;
    /**
     * 当前登陆的三方平台[qq.github.gitee]
     */
    private String platform;

    public AccessToken() {
    }

    public AccessToken(String tokenStr, String thirdUserId, String nickName, String headUrl, String platform) {
        this.tokenStr = tokenStr;
        this.platform = platform;
        setThirdUserId(thirdUserId);
        setNickName(nickName);
        setHeadUrl(headUrl);
    }

    public String getTokenStr() {
        return tokenStr;
    }

    public void setTokenStr(String tokenStr) {
        this.tokenStr = tokenStr;
    }

    public String getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(String expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public boolean isRememberMe() {
        return this.rememberMe;
    }

    /**
     * 获取认证的主体
     *
     * @return 返回主体信息
     */
    @Override
    public Object getPrincipal() {
        return getUserName();
    }

    /**
     * 获取认证主体的密码密文
     *
     * @return 返回密文
     */
    @Override
    public Object getCredentials() {
        return getPassword();
    }

    @Override
    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
