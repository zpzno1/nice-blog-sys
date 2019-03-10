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

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.service.IMarkdownStoreageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 七牛云文件上传下载控制器
 *
 * @author kiwipeach
 * @create 2019-02-23
 */
@Controller
@RequestMapping("qiniu")
public class CommonQiNiuYunController {

    @Autowired
    private IMarkdownStoreageService iMarkdownStoreageService;

    /**
     * 上传单个文件
     *
     * @param multipartFile 上传文件
     * @return 返回上传单个文件结果
     */
    @ResponseBody
    @PostMapping("singleUpload")
    public AjaxResponse<String> upload(@RequestParam("fileName") MultipartFile multipartFile) {
        String uploadResult = iMarkdownStoreageService.upload(multipartFile);
        return AjaxResponse.success(uploadResult);
    }

    /**
     * 上传多个文件
     *
     * @param files 上传文件列表
     * @return 返回多个文件下载列表
     */
    @ResponseBody
    @PostMapping("multiUpload")
    public AjaxResponse<List<String>> upload(@RequestParam("fileName") List<MultipartFile> files) {
        List<String> uploadResultList = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String upload = iMarkdownStoreageService.upload(multipartFile);
            uploadResultList.add(upload);
        }
        return AjaxResponse.success(uploadResultList);
    }

    /**
     * 获取指定文件的下载地址
     *
     * @param fileId 目标下载文件编号
     * @return 返回下载地址
     */
    @ResponseBody
    @PostMapping("download")
    public AjaxResponse<String> download(String fileId) {
        return AjaxResponse.success(iMarkdownStoreageService.download(fileId));
    }

}
