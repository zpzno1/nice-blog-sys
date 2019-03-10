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


import cn.kiwipeach.blog.anno.AccessLog;
import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import cn.kiwipeach.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    @Qualifier("blogServiceImpl")
    private IBlogService iBlogService;

    /**
     * 分页查询博客信息
     *
     * @param page       分页入参
     * @param categoryId 分类编号
     * @param tagName    标签名称
     * @return 返回博客分页信息
     */
    @GetMapping("query")
    @ResponseBody
    public AjaxResponse<IPage> pageQuery(Page page,
                                         @RequestParam(required = false, value = "categoryId") String categoryId,
                                         @RequestParam(required = false, value = "tagName") String tagName) {
        IPage iPage = iBlogService.pageQuery(page, categoryId, tagName);
        return AjaxResponse.success(iPage);
    }

    /**
     * 查询博客详情
     *
     * @param blogId 博客编号
     * @param model  域模型
     * @return 返回单条博客详情信息
     */
    @GetMapping("detail/{blogId}")
    public String queryBlogDetail(@PathVariable("blogId") String blogId, Model model) {
        BlogInfoVO blogInfoVO = iBlogService.queryById(blogId);
        model.addAttribute("blogDetail", blogInfoVO);
        return "blog/detail";
    }

    /**
     * 分页查询博客归档信息
     *
     * @param page    分页入参
     * @param pattern 博客归档格式（yyyy,yyyyMM,yyyyQ）
     * @return 返回博客归档列表信息
     */
    @AccessLog("日志归档信息查询")
    @GetMapping(value = "archive/query", produces = "application/json")
    @ResponseBody
    public AjaxResponse<IPage<ArchiveBlogTimelineVO>> archiveBlogQuery(Page page,
                                                                       @RequestParam(value = "pattern", defaultValue = "yyyy") String pattern,
                                                                       HttpServletResponse response) {
        IPage iPage = iBlogService.archiveBlogQuery(page, pattern);
        return AjaxResponse.success(iPage);
    }

}
