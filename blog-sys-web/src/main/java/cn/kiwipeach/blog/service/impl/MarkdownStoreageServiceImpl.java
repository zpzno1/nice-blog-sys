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

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.IBlogService;
import cn.kiwipeach.blog.service.IMarkdownStoreageService;
import cn.kiwipeach.blog.store.QiNiuConfigProperties;
import com.alibaba.fastjson.JSONObject;
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

import java.io.*;
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

    @Autowired
    private IBlogService iBlogService;


    @Override
    public String upload(MultipartFile multipartFile) {
        //1）自动获取存储区域（华东、华北、华南）配置对象。
        Configuration c = new Configuration(Zone.autoZone());
        //2）初始化上传对象
        UploadManager uploadManager = new UploadManager(c);
        String token = Auth.create(qiNiuConfig.getAccessKey(), qiNiuConfig.getPrivateKey()).uploadToken(qiNiuConfig.getMarkdownBucket());
        String resultBodyString = null;
        try {
            StringMap params = new StringMap();
            //3)开始文件上传 20190223-Java开发日常规范.md
            String fileKey = new StringBuffer(new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())).append("-").append(multipartFile.getOriginalFilename()).toString();
            InputStream inputStream = multipartFile.getInputStream();
            Blog blog = analyzeBlogContent(inputStream);
            //4)设置博客其他信息
            Response res = uploadManager.put(multipartFile.getInputStream(), fileKey, token, params, "application/octet-stream");
            resultBodyString = res.bodyString();
            JSONObject uploadResult = JSONObject.parseObject(resultBodyString);
            blog.setUserId("kiwipeach");
            blog.setTitle(multipartFile.getOriginalFilename());
            String downloadUrl = download(uploadResult.getString("key"));
            blog.setContent(downloadUrl);
            blog.setContentType("0");//博客内容类型[0:千牛markdown类型 1:markdown文本类型 2:个性化网页内容型]
            boolean isSave = iBlogService.save(blog);
            log.info("博客入库状态:{},上传七牛云存储状态:{}", isSave, resultBodyString);
        } catch (IOException e) {
            throw new BlogException("-SINGLE_UPLOAD_01", e.getMessage());
        }
        return resultBodyString;
    }

    /**
     * 分析上传的博客文件，获取博客的图标和简述信息
     *
     * @param inputStream 博客输入流
     * @return 返回博客分析出来的博客信息
     * @throws IOException 异常
     */
    private Blog analyzeBlogContent(InputStream inputStream) throws IOException {
        Blog blog = new Blog();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        String fistIconUrl = null;
        int lineNuber = 0;
        StringBuffer introduction = new StringBuffer();
        StringBuffer contentBuffer = new StringBuffer();
        log.debug("上传博客内网:");
        while ((line = bufferedReader.readLine()) != null) {
            lineNuber++;
            //获取博客简介
            if (lineNuber <= 3) {
                introduction.append(line);
                log.debug(line);
            }
            //获取博客图标
            if (line.startsWith("![") && line.endsWith(".png)")) {
                int begin = line.indexOf("(http://");
                int end = line.lastIndexOf(")");
                fistIconUrl = line.substring(begin + 1, end);
            }
            contentBuffer.append(line);
        }
        blog.setIntroduction(introduction.toString());
        blog.setIconUrl(fistIconUrl);
        return blog;
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
