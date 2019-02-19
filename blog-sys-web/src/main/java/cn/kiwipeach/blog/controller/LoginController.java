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
import cn.kiwipeach.blog.enums.CodeEnum;
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
import org.springframework.web.bind.annotation.*;

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


    @Autowired(required = false)
    @Qualifier("githubLoginServiceImpl")
    private ILoginService githubLoginService;

    @Autowired(required = false)
    @Qualifier("giteeLoginServiceImpl")
    private ILoginService giteeLoginService;


    /***************************************************三方通用登陆 begin**************************************************************/

    /**
     * 三大授权平台的认证授权登陆入口
     *
     * @param platform 平台[qq.github.gitee]
     * @return 返回登陆地址
     */
    @RequestMapping("{platform}/login")
    public String toQQLoginPage(@PathVariable("platform") String platform) {
        ILoginService loginService = decideLoginService(platform);
        return "redirect:" + loginService.queryLoginUrl();
    }


    /**
     * 三大授权平台的授权回调地址
     *
     * @param code     qq授权码
     * @param state    状态码,码云没有传该值，qq和github有使用
     * @param platform 平台[qq.github.gitee]
     * @return 返回登陆结果页面
     */
    @RequestMapping("{platform}/oauth2.0/callback")
    public String qqLoginCallback(
            @RequestParam(name = "code", required = true) String code,
            @RequestParam(name = "state", required = false) String state,
            @PathVariable("platform") String platform,
            @RequestParam(name = "remember", required = false) boolean remember,
            HttpServletRequest request) {
        log.info("{}平台登陆开始:code={},state={}", platform, code, state);
        ILoginService loginService = decideLoginService(platform);
        if (loginService == null) {
            throw new BlogException("-LOGIN_02", "三方登陆配置有误，请联系管理员");
        } else {
            AccessToken accessToken = loginService.login(code);
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
        log.info("{}平台登陆成功:{}", targetUrl);
        return "redirect:" + targetUrl;
    }


    /**
     * 选择三方平台服务
     *
     * @param platform 平台类型
     * @return 返回登陆服务实现类
     */
    private ILoginService decideLoginService(String platform) {
        ILoginService loginService = null;
        if (CodeEnum.QQ.toString().equalsIgnoreCase(platform)) {
            loginService = qqLoginService;
        } else if (CodeEnum.GITHUB.toString().equalsIgnoreCase(platform)) {
            loginService = githubLoginService;
        } else if (CodeEnum.GITEE.toString().equalsIgnoreCase(platform)) {
            loginService = giteeLoginService;
        } else {
            throw new IllegalArgumentException("非法入参:" + platform);
        }
        return loginService;
    }

    /***************************************************三方通用登陆 end**************************************************************/


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
        return "redirect:/blog/index.html";
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
