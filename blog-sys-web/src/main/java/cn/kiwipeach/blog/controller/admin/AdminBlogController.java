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
package cn.kiwipeach.blog.controller.admin;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博客上传控制器
 *
 * @author kiwipeach
 * @create 2019-03-04
 */
@Controller
@RequestMapping("admin")

public class AdminBlogController {

    @Autowired
    @Qualifier("adminBlogServiceImpl")
    private IBlogService iBlogService;

    @ResponseBody
    @PostMapping("blog/upload")
    public AjaxResponse upload(@RequestParam("fileName") MultipartFile multipartFile) {
        return iBlogService.uploadBlog(multipartFile);
    }

}
