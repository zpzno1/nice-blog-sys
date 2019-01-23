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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 *
 * @author kiwipeach
 * @create 2019-01-24
 */
@Controller
public class PageCommonController {
    /**
     * 博客首页显示
     * @return
     */
    @RequestMapping("blog/index")
    public String toBlogIndexPage(){
        return "blog/index";
    }
    /**
     * 博客详情页面
     * @return
     */
    @RequestMapping("blog/detail")
    public String toBlogDetailPage(){
        return "blog/detail";
    }

    /**
     * 博客归档页面
     * @return
     */
    @RequestMapping("blog/archive")
    public String toBlogArchivePage(){
        return "blog/archive";
    }

    /**
     * 博客关于页面
     * @return
     */
    @RequestMapping("blog/about")
//    @RequiresPermissions(value = "blog:page:about")
    public String toBlogAboutPage(){
        return "blog/about";
    }
}
