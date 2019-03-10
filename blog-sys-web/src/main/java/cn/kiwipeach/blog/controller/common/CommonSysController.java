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
package cn.kiwipeach.blog.controller.common;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.vo.UserInfoVO;
import cn.kiwipeach.blog.exception.BlogException;
import org.apache.shiro.SecurityUtils;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客系统基础控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/11
 */
@Controller
public class CommonSysController {

    /**
     * 获取当前登陆用户信息
     *
     * @return 返回当前登陆用户信息
     */
    @GetMapping("user")
    @ResponseBody
    public AjaxResponse<UserInfoVO> queryUserInfo() {
        AccessToken curUser = (AccessToken) SecurityUtils.getSubject().getPrincipal();
        if (curUser == null) {
            return AjaxResponse.fail("-USERINFO_OO1", "未查询到用户信息");
        }else {
            UserInfoVO userInfoVO = new UserInfoVO(curUser.getUserName(), curUser.getNickName(), curUser.getHeadUrl());
            return AjaxResponse.success(userInfoVO);
        }
    }

    @GetMapping("exception")
    public String test500InternalException() {
        if (true) {
            throw new BlogException("-BLOG_TEST", "测试SpringBoot业务异常");
        }
        return null;
    }


}
