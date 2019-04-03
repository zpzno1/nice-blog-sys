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

import cn.kiwipeach.blog.anno.AccessLog;
import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.enums.PlatForm;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.ILoginService;
import cn.kiwipeach.blog.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired(required = true)
    @Qualifier("systemLoginService")
    private ILoginService systemLoginService;

    @Autowired
    private ISysUserService iSysUserService;


    /***************************************************三方通用登陆 begin**************************************************************/

    /**
     * 三大授权平台的认证授权登陆入口
     *
     * @param platform 平台[qq.github.gitee]
     * @return 返回登陆地址
     */
    @RequestMapping("{platform}/login")
    public String toLoginPage(@PathVariable("platform") String platform) {
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
    @AccessLog("三方授平台登陆")
    public String loginCallback(
            @RequestParam(name = "code", required = true) String code,
            @RequestParam(name = "state", required = false) String state,
            @PathVariable("platform") String platform,
            @RequestParam(name = "remember", defaultValue = "1") boolean remember,
            HttpServletRequest request) {
        log.info("{}平台登陆开始:code={},state={}", platform, code, state);
        ILoginService loginService = decideLoginService(platform);
        if (loginService == null) {
            throw new BlogException("-LOGIN_02", "三方登陆配置有误，请联系管理员");
        } else {
            AccessToken accessToken = loginService.login(code);
            //记录平台信息
            accessToken.setPlatform(platform);
            accessToken.setUserName(accessToken.getPlatform() + "_" + accessToken.getUserName());
            Wrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().eq("THIRD_USER_ID", accessToken.getThirdUserId());
            SysUser dbQueryUser = iSysUserService.getOne(queryWrapper);
            if (dbQueryUser == null) {
                //希望用户修改自己的默认名称，所以这里以"平台+昵称"的方式注册默认用户昵称
                boolean createSuccess = iSysUserService.createSysUser(accessToken);
                if (createSuccess) {
                    log.warn("新用户注册成功!用户名:{}", accessToken.getUserName());
                }
            } else {
                //将数据库的用户编号维护到AccessToken中
                accessToken.setId(dbQueryUser.getId());
            }
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
        if (PlatForm.QQ.toString().equalsIgnoreCase(platform)) {
            loginService = qqLoginService;
        } else if (PlatForm.GITHUB.toString().equalsIgnoreCase(platform)) {
            loginService = githubLoginService;
        } else if (PlatForm.GITEE.toString().equalsIgnoreCase(platform)) {
            loginService = giteeLoginService;
        } else if (PlatForm.SYSTEM.toString().equals(platform)) {
            loginService = systemLoginService;
        } else {
            throw new IllegalArgumentException("非法入参:" + platform);
        }
        return loginService;
    }

    /***************************************************三方通用登陆 end**************************************************************/


    //@GetMapping("login")
    //public String toLoginPage() {
    //    return "shiro/login";
    //}

    /**
     * 未登录返回json，不返回页面了
     *
     * @return 返回未登录数据
     */
    @GetMapping("login")
    @ResponseBody
    public AjaxResponse toLoginPage(HttpServletRequest request) {
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        String requestUrl = savedRequest.getRequestUrl();
        String resultMsg = String.format("当前用户处于未登录状态，请登录后重试!");
        AjaxResponse ajaxResponse = new AjaxResponse("401", resultMsg);
        ajaxResponse.setData(requestUrl);
        return ajaxResponse;
    }


    @AccessLog("系统用户登陆")
    @PostMapping("blog/user/login")
    public String userLoginAction(SysUser loginUser, @RequestParam(value = "remember", defaultValue = "0") Boolean remember) {
        Subject currentUser = SecurityUtils.getSubject();
        //2.判断是否认证过，否则进行重新认证
        if (!currentUser.isAuthenticated()) {
            //UsernamePasswordToken token = new UsernamePasswordToken(secUser.getUserName(), secUser.get());
            //在此类org.kiwipeach.blog.shiro.realam.CustomShiroRealm中配置了自定义Realm需要认证授权的Realam对象的类型。
            AccessToken accessToken = new AccessToken();
            //TODO 对于本博客平台（system）的token采用置顶的认证方式
            accessToken.setPlatform("system");
            accessToken.setUserName(loginUser.getUserName());
            accessToken.setPassword(loginUser.getPassword());
            //UsernamePasswordToken token = new UsernamePasswordToken(loginUser.getUserName(), loginUser.getPassword());
            //1.设置rememberMe
            //token.setRememberMe(remember);
            accessToken.setRememberMe(remember);
            try {
                currentUser.login(accessToken);
            } catch (DisabledAccountException e) {
                throw new BlogException("-LOGIN_001", "博客账号已经被禁用，请联系系统管理员(1099501218@qq.com)");
            } catch (ConcurrentAccessException e) {
                throw new BlogException("-LOGIN_001", "账号已经在异地登录");
            } catch (ExcessiveAttemptsException e) {
                throw new BlogException("-LOGIN_001", "用户登录设备到达上线");
            } catch (UnknownAccountException e) {
                throw new BlogException("-LOGIN_001", "该账号不存在");
            } catch (IncorrectCredentialsException e) {
                throw new BlogException("-LOGIN_001", "密码不正确");
            } catch (ExpiredCredentialsException e) {
                throw new BlogException("-LOGIN_001", "密码超期，请及时更新");
            } catch (AuthenticationException e) {
                throw new BlogException("-LOGIN_001", "用户登录异常");
            }
        }
        //登陆成功，则用户认证成功，进入成功页面；登陆失败，则认证失败，会被拦截到登陆地址。
        return "redirect:/login/success";
    }

    @GetMapping("login/success")
    public String toLoginSuccessPage() {
        return "redirect:/";
    }

    @GetMapping("login/fail")
    public String toLoginFailPage() {
        return "shiro/fail";
    }

    //@GetMapping("/unauthorized")
    //public String toUnauthorizedPage() {
    //    return "shiro/unauthorized";
    //}


    @GetMapping("/unauthorize")
    @ResponseBody
    public AjaxResponse toUnauthorizedPage(HttpServletRequest request) {
        AjaxResponse ajaxResponse = new AjaxResponse("403", "当前没有对此资源的操作权限，请联系管理员(1099501218@qq.com)！");
        return ajaxResponse;
    }

    @GetMapping("/remember")
    public String toRememberPage() {
        return "shiro/remember";
    }

}
