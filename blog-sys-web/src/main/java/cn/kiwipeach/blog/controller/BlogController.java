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


import cn.kiwipeach.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客 前端控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private IBlogService iBlogService;

    /**
     *  使用BaseService中的分页函数
     *  http://localhost:8825/blog/page1
     */
    @RequestMapping("page1")
    @ResponseBody
    public IPage page1(Page page) {
        return iBlogService.selectPage(page, null);
    }

    /**
     * 使用自定义的分页查询
     * http://localhost:8825/blog/page2?userId=10086&size=3
     */
    @RequestMapping("page2")
    @ResponseBody
    public IPage page2(Page page, @RequestParam(value = "userId",required = true)String userId) {
        return iBlogService.selectBlogPage(page,userId);
    }




}
