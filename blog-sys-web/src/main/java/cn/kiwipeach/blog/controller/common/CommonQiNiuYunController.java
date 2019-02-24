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

import cn.kiwipeach.blog.configuration.AjaxResponse;
import cn.kiwipeach.blog.service.IMarkdownStoreageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 七牛云文件上传下载控制器
 *
 * @author kiwipeach
 * @create 2019-02-23
 */
@Controller
@RequestMapping("qiniu")
@Slf4j
public class CommonQiNiuYunController {

    @Autowired
    private IMarkdownStoreageService iMarkdownStoreageService;

    @ResponseBody
    @PostMapping("singleUpload")
    public AjaxResponse upload(@RequestParam("fileName") MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        log.info("文件上传名称：{}", fileName);
        String upload = iMarkdownStoreageService.upload(multipartFile);
        return AjaxResponse.success(upload);
    }


    @ResponseBody
    @PostMapping("multiUpload")
    public AjaxResponse upload(@RequestParam("fileName") List<MultipartFile> files) {
        for (MultipartFile multipartFile : files) {
            String fileName = multipartFile.getOriginalFilename();
            log.info("文件上传名称：{}", fileName);
        }
        return AjaxResponse.success("上传成功");
    }

    @ResponseBody
    @PostMapping("download")
    public AjaxResponse download() {
        return null;
    }

}
