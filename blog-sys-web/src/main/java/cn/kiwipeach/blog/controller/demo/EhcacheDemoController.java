/*
 * Copyright 2019 liuburu@qq.com.
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
package cn.kiwipeach.blog.controller.demo;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.service.demo.IEhcacheDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * 描述：ehcache缓存案例测试
 *
 * @author kiwipeach
 * @create 2019-03-29
 */
@Controller
    @RequestMapping("/demo/ehcache")
public class EhcacheDemoController {


    @Autowired
    private IEhcacheDemoService iEhcacheDemoService;

    @Autowired
    CacheManager cacheManager;

    @PostConstruct
    public void init() {

    }



    @GetMapping("reloadCache")
    @ResponseBody
    public AjaxResponse<String> reloadCache(Blog blog) {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        // 重置缓存
        for (String cacheName : cacheNames) {
            Cache cache = cacheManager.getCache(cacheName);
            cache.clear();
        }
        return AjaxResponse.success("重置缓存成功");
    }


    @GetMapping("create")
    @ResponseBody
    public AjaxResponse<Blog> createBlog(Blog blog) {
        Blog saveBlog = iEhcacheDemoService.save(blog);
        return AjaxResponse.success(saveBlog);
    }

    @GetMapping("delete/{blogId}")
    @ResponseBody
    public AjaxResponse<Boolean> deleteBlogById(@PathVariable("blogId") String blogId) {
        boolean delete = iEhcacheDemoService.deleteById(blogId);
        return AjaxResponse.success(delete);
    }

    @GetMapping("query/{blogId}")
    @ResponseBody
    public AjaxResponse<Blog> queryBlogById(@PathVariable("blogId") String blogId) {
        Blog blog = iEhcacheDemoService.selectById(blogId);
        return AjaxResponse.success(blog);
    }

    @GetMapping("update")
    @ResponseBody
    public AjaxResponse<Blog> updateBlog(Blog blog) {
        Blog updateBlog = iEhcacheDemoService.updateById(blog);
        return AjaxResponse.success(updateBlog);
    }


}
