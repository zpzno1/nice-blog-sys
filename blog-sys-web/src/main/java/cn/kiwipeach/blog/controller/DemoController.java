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


import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.IDemoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客 前端控制器  【开播提醒群号:679722876】，不定时随缘直播，今天调试直播间，不写代码
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    private IDemoService iDemoService;

    @GetMapping("hello")
    public String test(Model model) {
        model.addAttribute("word", "hello kiwipeach");
        return "demo/demo";
    }

    /**
     * 使用BaseService中的分页函数
     * http://localhost:8825/demo/page1?size=2&current=2
     */
    @RequestMapping("page1")
    @ResponseBody
    public IPage page1(Page page) {
        return iDemoService.page(page, null);
    }

    /**
     * 使用自定义的分页查询
     * http://localhost:8825/demo/page2?userId=10086&size=2&current=2
     */
    @RequestMapping("page2")
    @ResponseBody
    public IPage page2(Page page, @RequestParam(value = "userId", required = true) String userId) {
        return iDemoService.queryBlogListDemo(page, userId);
    }

    @RequestMapping("500")
    @ResponseBody
    public String internalServrError() {
        if (true) {
            throw new BlogException(999, "测试异常");
        }
        return "tets";
    }


}
