package cn.kiwipeach.blog.service;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.kiwipeach.blog.BlogWebApplicationTests;
import cn.kiwipeach.blog.email.EmailBody;
import cn.kiwipeach.blog.email.EmailContentDatasource;
import cn.kiwipeach.blog.enums.EmailContentType;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

@Slf4j
public class IEmailServiceTest extends BlogWebApplicationTests {

    @Autowired
    private IEmailService iEmailService;

    @Test
    public void testClassFile() throws IOException {
        //获取文件
        File file = new File(ResourceUtils.getFile("classpath:email/blog_comment_reply.html").getPath());
        log.warn("文件是否存在：{}", file.exists());

        //获取文件流
        ClassPathResource classPathResource = new ClassPathResource("classpath:email/blog_comment_reply.html");
        InputStream stream = classPathResource.getStream();
        log.warn("stream流是否为空？{}", stream == null);
        stream.close();

        //获取jar包中的
        InputStream inputStream = EmailBody.class.getResourceAsStream("/email/blog_comment_reply.html");
        log.warn("stream流是否为空？{}", inputStream == null);
        inputStream.close();

    }

    @Test
    public void 发送带有格式的Html邮件() throws Exception {
        EmailBody emailBody = new EmailBody();
        //收件人+抄送人+密送人
        emailBody.setToEmails(Arrays.asList("liuburu@qq.com"));
        //emailBody.setCcEmails(Arrays.asList("liuburu@outlook.com"));
        //emailBody.setBccList(Arrays.asList("liuburu@outlook.com"));
        //附件
        //List<JSONObject> attrachmentFiles = new ArrayList<>();
        //JSONObject jsonObject1 = new JSONObject();
        //jsonObject1.put("url", "http://img.kiwipeach.cn/02203529535d5f38911156593eb2dddc.png");
        //jsonObject1.put("name", "老婆.png");
        //JSONObject jsonObject2 = new JSONObject();
        //jsonObject2.put("url", "http://img.kiwipeach.cn/90e51784904916a9a19c8f3882902b5b.png");
        //jsonObject2.put("name", "博客网站.png");
        //attrachmentFiles.add(jsonObject1);
        //attrachmentFiles.add(jsonObject2);
        //emailBody.setAttachmentFiles(attrachmentFiles);
        emailBody.setSubject("你好，卜铷，测试邮件");
        Set<String> paramSet = new LinkedHashSet<>();
        paramSet.add("在乎你的在乎");
        paramSet.add("#");
        paramSet.add("收益匪浅啊，兄弟！");
        paramSet.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        EmailContentDatasource contentSource = new EmailContentDatasource(paramSet);
        emailBody.setContentDatasource(contentSource);
        emailBody.setEmailContentType(EmailContentType.MESSAGE_FORMAT_OF_BLOG_COMMENT_REPLY);
        boolean b = iEmailService.sendEmail(emailBody);
        log.info("邮件发送成功？{}", b);
    }
}