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

import cn.kiwipeach.blog.service.ILoginService;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.stereotype.Service;

/**
 * github登陆服务实现类
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
@Service
public class GithubLoginServiceImpl implements ILoginService {
    @Override
    public AccessToken login(String code) {
        /**
         * 登陆成功：access_token=2544e25b1027631ab43d94373e2fb984fd3b92d7&scope=&token_type=bearer
         * 登陆失败：error=bad_verification_code&error_description=The+code+passed+is+incorrect+or+expired.&error_uri=https%3A%2F%2Fdeveloper.github.com%2Fapps%2Fmanaging-oauth-apps%2Ftroubleshooting-oauth-app-access-token-request-errors%2F%23bad-verification-code
         */
        return null;
    }
}
