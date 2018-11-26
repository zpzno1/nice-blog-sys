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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统页面跳转控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/11/26
 */
@Controller
@RequestMapping("admin")
public class SysPageController {

    /**
     * 首页跳转
     *
     * @return 首页
     */
    @RequestMapping("index")
    public String toAdminIndexPage(HttpServletRequest request) {
        String contextPath = request.getContextPath() + "/";
        request.setAttribute("ctx", contextPath);
        return "index";
    }

    /**
     * Home地址
     *
     * @return 首页
     */
    @RequestMapping("home")
    public String toAdminHomePage() {
        return "home";
    }

    /**
     * blog模块相关地址跳转
     *
     * @return 首页
     */
    @RequestMapping("{module}/{business}/{operation}")
    public String toAdminBlogModulePage(
            @PathVariable("module") String module,
            @PathVariable("business") String business,
            @PathVariable("operation") String operation) {
        return module + "/" + business + "/" + operation;
    }

}
