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
package cn.kiwipeach.blog.controller;

import cn.kiwipeach.blog.exception.BlogException;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * qq登陆控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/13
 */
@Controller
@RequestMapping("qq")
@Slf4j
public class QQLoginController {

    /**
     * 跳转到QQ的登陆地址
     *
     * @return QQ登陆页面
     */
    @RequestMapping("login")
    public String toQQLoginPage(HttpServletRequest request) {
        String authorizeURL = null;
        try {
            authorizeURL = new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            log.error("QQ登陆错误:", e);
            throw new BlogException(e);
        }
        authorizeURL = new StringBuffer("redirect:").append(authorizeURL).toString();
        log.info("请求QQ登陆地址:{}", authorizeURL);
        return authorizeURL;
    }


    /**
     *  QQ登陆成功后的回调地址
     * @return 登陆成功或失败地址
     */
    @RequestMapping("afterlogin")
    public String qqCallBackAction() {
        return null;
    }

}
