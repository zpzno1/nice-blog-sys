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
package cn.kiwipeach.blog.oauth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * qq登陆控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/13
 */
@Controller
@RequestMapping("qq/oauth2.0")
@Slf4j
public class QQLoginController {

    @RequestMapping("login")
    public String toQQLoginPage() {
        return null;
    }


    @RequestMapping("callback")
    public String qqLoginActionCallback() {
        return null;
    }

}
