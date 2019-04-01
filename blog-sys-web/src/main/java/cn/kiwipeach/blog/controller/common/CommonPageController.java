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

import cn.kiwipeach.blog.domain.vo.SysBannerInvoVO;
import cn.kiwipeach.blog.service.IBlogCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转控制器,分三种类型：
 * 1）前端普通页面跳转
 * 2）后端普通页面跳转
 * 3）前端授权页面跳转
 * 4）后端授权页面挑战
 *
 * @author kiwipeach
 * @create 2019-01-24
 */
@Controller
public class CommonPageController {

    @Autowired
    private IBlogCommService iBlogCommService;


    /**
     * 1）前端通用页面跳转
     *
     * @return 首页
     */
    @RequestMapping("{business}/{pageName}.html")
    public String frontResponseHtml(
            @PathVariable("business") String business,
            @PathVariable("pageName") String pageName,
            ModelMap modelMap) {
        StringBuffer targetUrl = new StringBuffer(business).append("/").append(pageName);
        SysBannerInvoVO sysBannerInvoVO = iBlogCommService.querySysBannerInfo();
        //当前系统banner信息
        modelMap.put("sysBannerInvoVO", sysBannerInvoVO);
        return targetUrl.toString();
    }

    /**
     * 2）前端通用数据接口
     *
     * @return 首页
     */
    @RequestMapping("{business}/{pageName}.json")
    @ResponseBody
    public String frontResponseJson(
            @PathVariable("business") String business,
            @PathVariable("pageName") String pageName) {
        StringBuffer targetUrl = new StringBuffer(business).append("/").append(pageName);
        return targetUrl.toString();
    }


    /**
     * 1）前端通用页面跳转
     *
     * @return 首页
     */
    @RequestMapping("admin/{pageName}.html")
    public String adminResponseHtml(
            @PathVariable("pageName") String pageName, Model model, HttpServletRequest request) {
        StringBuffer targetUrl = new StringBuffer("admin").append("/").append(pageName);
        String contextPath = request.getContextPath() + "/";
        //request.setAttribute("ctx", contextPath);
        model.addAttribute("ctx", contextPath);
        return targetUrl.toString();
    }

    /**
     * 3）后端普通页面跳转 admin/system/blog/send.html
     *
     * @return 首页
     */
    @RequestMapping("admin/{module}/{business}/{pageName}.html")
    public String adminResponseHtml(
            @PathVariable("module") String module,
            @PathVariable("business") String business,
            @PathVariable("pageName") String pageName,
            HttpServletRequest request) {
        StringBuffer targetUrl = new StringBuffer("admin/").append(module).append("/").append(business).append("/").append(pageName);
        String contextPath = request.getContextPath() + "/";
        request.setAttribute("ctx", contextPath);
        return targetUrl.toString();
    }

    /**
     * 4）后端普数据接口
     *
     * @return 首页
     */
    @RequestMapping("admin/{module}/{business}/{pageName}.json")
    public String adminResponseJson(
            @PathVariable("module") String module,
            @PathVariable("business") String business,
            @PathVariable("pageName") String pageName,
            HttpServletRequest request) {
        StringBuffer targetUrl = new StringBuffer("admin/").append(module).append("/").append(business).append("/").append(pageName);
        String contextPath = request.getContextPath() + "/";
        request.setAttribute("ctx", contextPath);
        return targetUrl.toString();
    }


}
