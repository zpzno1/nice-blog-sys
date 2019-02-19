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
package cn.kiwipeach.blog.controller.common;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后端授权页面跳转
 *
 * @author kiwipeach
 * @create 2019-01-24
 */
@Controller
public class CommonAdminAuthorizeController {


    /**
     * 博客关于页面
     *
     * @return
     */
    @RequestMapping(value = "amdin/blog/delete", method = RequestMethod.GET)
    @RequiresPermissions(value = "admin:blog:delete")
    @ResponseBody
    public String toBlogAboutPage() {
        //FIXME 有更明确的url，会不会先找更加明确地地址呢？
        return "true";
    }

}
