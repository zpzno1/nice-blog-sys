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
package cn.kiwipeach.blog.service;

import org.kiwipeach.blog.shiro.token.AccessToken;

/**
 * 三方平台登陆服务接口
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
public interface ILoginService {


    /**
     * 通过授权码获取当用户的登陆信息
     *
     * @param code 授权码
     * @return 返回博客统一用户信息
     */
    AccessToken login(String code);

    /**
     * 获取三方平台的登陆地址
     *
     * @return 返回认证登陆地址
     */
    String queryLoginUrl();

}
