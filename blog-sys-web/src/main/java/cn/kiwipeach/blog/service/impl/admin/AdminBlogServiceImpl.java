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
package cn.kiwipeach.blog.service.impl.admin;

import cn.kiwipeach.blog.configuration.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.adapter.AdminBlogServiceAdapter;
import cn.kiwipeach.blog.utils.BlogRegexUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 后台博客服务相关实现类
 *
 * @author kiwipeach
 * @create 2019-03-04
 */
@Service
public class AdminBlogServiceImpl extends AdminBlogServiceAdapter {

    @Override
    public AjaxResponse<Boolean> uploadBlog(MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Blog blog = analyzeBlogContent(inputStream);
            //FIXME 需要登录才能进行文件上传操作
            blog.setUserId("kiwipeach");
            blog.setTitle(multipartFile.getOriginalFilename());
            //博客内容类型[0:markdown 1:html）]
            blog.setContentType("0");
            boolean isSave = save(blog);
            return AjaxResponse.success(isSave);
        } catch (IOException e) {
            throw new BlogException("-SINGLE_UPLOAD_01", e.getMessage());
        }
    }

    /**
     * 分析上传的博客文件，获取博客的图标、简述信息、内容信息
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
        StringBuffer contentBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            //分析获取博客图标
            if (line.startsWith("![") && line.endsWith(".png)")) {
                int begin = line.indexOf("(http://");
                int end = line.lastIndexOf(")");
                fistIconUrl = line.substring(begin + 1, end);
            }
            contentBuffer.append(line);
        }
        String analyseMarkdown = BlogRegexUtil.analyseMarkdown(contentBuffer.toString());
        blog.setIntroduction(analyseMarkdown);
        blog.setContent(contentBuffer.toString());
        if (fistIconUrl == null) {
            fistIconUrl = "assets/images/index/blog-default.png";
        }
        blog.setIconUrl(fistIconUrl);
        return blog;
    }
}
