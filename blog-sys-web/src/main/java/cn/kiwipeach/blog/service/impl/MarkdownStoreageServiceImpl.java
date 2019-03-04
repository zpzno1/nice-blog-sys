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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.IMarkdownStoreageService;
import cn.kiwipeach.blog.store.QiNiuConfigProperties;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * markdonw博客文件存储服务实现类
 *
 * @author kiwipeach
 * @create 2019-02-23
 */
@Service
@Slf4j
public class MarkdownStoreageServiceImpl implements IMarkdownStoreageService {

    @Autowired
    private QiNiuConfigProperties qiNiuConfig;

    @Override
    public String upload(MultipartFile multipartFile) {
        //1）自动获取存储区域（华东、华北、华南）配置对象。
        Configuration c = new Configuration(Zone.autoZone());
        //2）初始化上传对象
        UploadManager uploadManager = new UploadManager(c);
        String token = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getPrivateKey()).uploadToken(qiNiuConfig.getMarkdownBucket());
        try {
            StringMap params = new StringMap();
            //3)开始文件上传 20190223-Java开发日常规范.md
            String fileKey = new StringBuffer(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())).append("-").append(multipartFile.getOriginalFilename()).toString();
            //4)设置博客其他信息
            Response res = uploadManager.put(multipartFile.getInputStream(), fileKey, token, params, "application/octet-stream");
            String resultBodyString = res.bodyString();
            log.info("上传七牛云存储返回:{}", resultBodyString);
            return resultBodyString;
        } catch (IOException e) {
            throw new BlogException("-SINGLE_UPLOAD_01", e.getMessage());
        }
    }


    @Override
    public String upload(List<MultipartFile> multipartFileList) {
        return null;
    }

    @Override
    public String download(String fileid) {
        Auth auth = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getPrivateKey());
        StringBuffer sbUrl = new StringBuffer(qiNiuConfig.getMarkdownDomain());
        sbUrl.append("/").append(fileid);
        return auth.privateDownloadUrl(sbUrl.toString(), qiNiuConfig.getExpireTime());
    }
}
