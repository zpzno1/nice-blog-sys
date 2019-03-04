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
package cn.kiwipeach.blog.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * markdonw博客文件存储服务
 *
 * @author kiwipeach
 * @create 2019-02-23
 */
public interface IMarkdownStoreageService {

    /**
     * 单个文件上传
     * @param multipartFile 单个文件上传
     * @return 返回上传结果描述
     */
    String upload(MultipartFile multipartFile);

    /**
     * 单个文件上传
     * @param multipartFileList 多个文件
     * @return 返回上传结果描述
     */
    String upload(List<MultipartFile> multipartFileList);

    /**
     * 文件下载
     * @param fileid 文件唯一标识
     * @return 返回文件下载链接
     */
    String download(String fileid);
}
