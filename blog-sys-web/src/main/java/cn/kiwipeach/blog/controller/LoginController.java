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
package cn.kiwipeach.blog.controller;

import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.ILoginService;
import cn.kiwipeach.blog.service.impl.QQLoginServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登陆控制器[qq,github,gitee]
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
@RequestMapping
@Controller
public class LoginController {

    @Autowired(required = false)
    @Qualifier("qqLoginServiceImpl")
    private ILoginService qqLoginService;

    /**
     * http://www.kiwipeach.cn/qqlogin/callback?code=02F2BCC543134404B33E2665DCFD1A0C&state=d4213204f14d585c48998f8ba330425
     *
     * @param code  qq授权码
     * @param state 状态码
     * @return 返回登陆结果
     */
    @RequestMapping("qq/oauth2.0/callback")
    public String login(
            @RequestParam(name = "code", required = true) String code,
            @RequestParam(name = "state", required = true) String state,
            @RequestParam(name = "remember", required = false) boolean remember) {
        if (qqLoginService == null) {
            throw new BlogException("-LOGIN_02", "qq登陆未配置有误，请联系管理员");
        } else {
            AccessToken accessToken = qqLoginService.login(code);
            Subject currentUser = SecurityUtils.getSubject();
            //2.判断是否认证过，否则进行重新认证
            if (!currentUser.isAuthenticated()) {
                accessToken.setRememberMe(remember);
                try {
                    currentUser.login(accessToken);
                } catch (AuthenticationException e) {
                    throw new BlogException("-LOGIN_001", "用户信息登陆失败:" + e.getLocalizedMessage());
                }
            }
        }
        return "redirect:/login/success";
    }


}
