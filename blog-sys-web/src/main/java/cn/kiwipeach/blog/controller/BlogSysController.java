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

import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.exception.BlogException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 博客系统基础控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/11
 */
@Controller
public class BlogSysController {

    @GetMapping("exception")
    public String test500InternalException() {
        if (true) {
            throw new BlogException(101, "测试SpringBoot业务异常");
        }
        return null;
    }

    //region shiro测试地址

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
        return "shiro/success";
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
    //endregion

}
