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


import cn.kiwipeach.blog.configuration.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("query")
    @ResponseBody
    public AjaxResponse<IPage> pageQuery(Page page) {
        // 按照置顶顺序和更新时间顺序
        //QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().orderByDesc("TOP").orderByDesc("UPDATE_TIME");
        //IPage page1 = iBlogService.page(page, queryWrapper);
        IPage iPage = iBlogService.pageQuery(page);
        return AjaxResponse.success(iPage);
    }
}
