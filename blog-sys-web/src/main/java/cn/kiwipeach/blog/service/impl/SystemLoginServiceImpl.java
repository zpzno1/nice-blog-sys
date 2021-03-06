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
 * 博客自身系统登录服务实现类
 *
 * @author kiwipeach
 * @create 2019-02-28
 */
@Service("systemLoginService")
public class SystemLoginServiceImpl implements ILoginService {

    @Override
    public AccessToken login(String code) {
        return null;
    }

    @Override
    public String queryLoginUrl() {
        return "/login";
    }
}
