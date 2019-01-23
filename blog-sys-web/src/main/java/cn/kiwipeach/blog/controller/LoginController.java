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

import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.ILoginService;
import cn.kiwipeach.blog.service.impl.QQLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆控制器[qq,github,gitee]
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
@RequestMapping
@Controller
@Slf4j
public class LoginController {

    @Autowired(required = false)
    @Qualifier("qqLoginServiceImpl")
    private ILoginService qqLoginService;

    @RequestMapping("qq/login")
    public String toQQLoginPage() {
        return "redirect:" + qqLoginService.queryLoginUrl();
    }

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
            @RequestParam(name = "remember", required = false) boolean remember,
            HttpServletRequest request) {
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
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        // 被拦截的地址
        String targetUrl = "/login/success";
        if (savedRequest != null) {
            targetUrl = savedRequest.getRequestUrl();
        }
        log.info("拦截到目标地址:{}", targetUrl);
        return "redirect:" + targetUrl;
    }


    @GetMapping("login")
    public String toLoginPage() {
        return "shiro/login";
    }

    @PostMapping("user/login")
    public String userLoginAction(SysUser loginUser, @RequestParam(value = "remember", defaultValue = "0") Boolean remember) {
        Subject currentUser = SecurityUtils.getSubject();
        //2.判断是否认证过，否则进行重新认证
        if (!currentUser.isAuthenticated()) {
            //UsernamePasswordToken token = new UsernamePasswordToken(secUser.getUserName(), secUser.get());
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(), loginUser.getPassword());
            //1.设置rememberMe
            token.setRememberMe(remember);
            try {
                currentUser.login(token);
            } catch (AuthenticationException e) {
                System.out.println("用户信息登陆失败,失败原因:" + e.getLocalizedMessage());
            }
        }
        //登陆成功，则用户认证成功，进入成功页面；登陆失败，则认证失败，会被拦截到登陆地址。
        return "redirect:/login/success";
    }

    @GetMapping("login/success")
    public String toLoginSuccessPage() {
        return "redirect:/blog/index";
    }

    @GetMapping("login/fail")
    public String toLoginFailPage() {
        return "shiro/fail";
    }

    @GetMapping("/unauthorized")
    public String toUnauthorizedPage() {
        return "shiro/unauthorized";
    }

    @GetMapping("/remember")
    public String toRememberPage() {
        return "shiro/remember";
    }


}
