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
package cn.kiwipeach.blog.email;

import cn.kiwipeach.blog.enums.EmailContentType;
import cn.kiwipeach.blog.exception.BlogException;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 描述：超文本邮件实体类
 *
 * @author kiwipeach
 * @create 2019-07-23
 */
@Getter
@Setter
@Slf4j
public class EmailBody {
    /**
     * 发送的目标邮箱
     */
    private List<String> toEmails = new ArrayList<>();
    /**
     * 抄送的邮箱
     */
    private List<String> ccEmails = new ArrayList<>();
    /**
     * 密送邮箱地址
     */
    private List<String> bccList = new ArrayList<>();
    /**
     * 邮件标题
     */
    private String subject;
    /**
     * 邮件内容(可能是纯文本，或者是参数)
     */
    private EmailContentDatasource contentDatasource = new EmailContentDatasource();

    /**
     * 内容类型：默认SIMPLE_TEXT类型
     */
    private EmailContentType emailContentType = EmailContentType.SIMPLE_TEXT;
    /**
     * 邮件附件：key存在返回请参考：org.apache.commons.mail.EmailAttachment
     * 例如：disposition=attachment,url=?,name=?
     */
    private List<JSONObject> attachmentFiles = new ArrayList<>();

    public EmailBody() {
    }

    public EmailBody(List<String> toEmails) {
        this.toEmails = toEmails;
    }

    public EmailBody(List<String> toEmails, String subject, String conent) {
        this.toEmails = toEmails;
        this.subject = subject;
        this.contentDatasource = new EmailContentDatasource(conent);
    }

    public EmailBody(List<String> toEmails, String subject, Set<String> paramSet) {
        this.toEmails = toEmails;
        this.subject = subject;
        this.contentDatasource = new EmailContentDatasource(paramSet);
    }


    /**
     * 获取带有占位符的email邮件模板类
     *
     * @return 返回邮件模板字符串
     */
    public String readEmailHtmlTemplete() throws IOException {
        if (this.emailContentType.equals(EmailContentType.SIMPLE_TEXT)) {
            throw new BlogException("-EMAIL_SEND_001", "博客类型参数错误：EmailContentType");
        }
        String templeteFileName = this.emailContentType.getContentTemplete();
        //ClassPathResource classPathResource = new ClassPathResource("classpath:" + templeteFileName);
        //log.warn("邮件模板文件是否存在?{}", classPathResource.getFile().exists());
        //InputStream inputStream = classPathResource.getInputStream();
        InputStream inputStream = this.getClass().getResourceAsStream(templeteFileName);
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = fileReader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            log.error("解析邮件模板({})出错.", templeteFileName);
        } finally {
            try {
                inputStream.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

}
