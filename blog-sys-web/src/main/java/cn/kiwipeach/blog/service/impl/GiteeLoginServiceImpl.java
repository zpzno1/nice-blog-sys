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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.oauth.gitee.ConfigProperties;
import cn.kiwipeach.blog.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.GiteeHttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

/**
 * gitee登陆服务实现类
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
@Service("giteeLoginServiceImpl")
@Slf4j
public class GiteeLoginServiceImpl implements ILoginService {

    @Autowired
    @Qualifier("giteeConfigProperties")
    private ConfigProperties giteeConfig;


    @Override
    public AccessToken login(String code) {
        AccessToken accessToken = null;
        try {
            // 1.获取accessToken
            String accessTokenString = GiteeHttpUtil.getAccessTokenString(giteeConfig, code, "access_token");
            // 2.获取用户信息
            accessToken = GiteeHttpUtil.getAccessToken(giteeConfig, accessTokenString);
        } catch (Exception e) {
            log.error("gitee登陆异常:", e);
            throw new BlogException("-LOGIN_001", e.getLocalizedMessage());
        }
        return accessToken;
    }

    @Override
    public String queryLoginUrl() {
        String loginUrl = null;
        try {
            loginUrl = GiteeHttpUtil.getLoginUrl(giteeConfig);
        } catch (URISyntaxException e) {
            throw new BlogException("-LOGIN_001", e.getLocalizedMessage());
        }
        return loginUrl;
    }

}
