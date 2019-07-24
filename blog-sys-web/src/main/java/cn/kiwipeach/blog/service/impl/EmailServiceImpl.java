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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.email.EmailBody;
import cn.kiwipeach.blog.email.EmailContentDatasource;
import cn.kiwipeach.blog.enums.EmailContentType;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.service.IEmailService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

/**
 * 描述：邮件发送服务实现类
 *
 * @author kiwipeach
 * @create 2019-07-24
 */
@Service
@Slf4j
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private HtmlEmail htmlEmail;

    @Override
    public boolean sendEmail(EmailBody emailBody) {
        log.info("邮箱内容：{}", JSON.toJSONString(emailBody));
        boolean isPassed = paramsCheck(emailBody);
        if (isPassed) {
            String sendInfo = sendEmailAction(emailBody);
            log.warn("邮件发送状态：{}", sendInfo);
            return true;
        }
        return false;
    }

    /**
     * 发送电子邮件
     *
     * @param emailBody
     * @return
     */
    private String sendEmailAction(EmailBody emailBody) {
        EmailContentDatasource contentDatasource = emailBody.getContentDatasource();
        try {
            //邮件主题
            htmlEmail.setSubject(emailBody.getSubject());
            //收件人
            htmlEmail.addTo((String[]) emailBody.getToEmails().toArray());
            //抄送人
            if (!emailBody.getCcEmails().isEmpty()) {
                htmlEmail.addCc((String[]) emailBody.getCcEmails().toArray());
            }
            //密送人
            if (!emailBody.getBccList().isEmpty()) {
                htmlEmail.addBcc((String[]) emailBody.getBccList().toArray());
            }
            //收件内容
            if (contentDatasource.getEmailContent().get(EmailContentDatasource.CONTENT_KEY) instanceof Set) {
                if (EmailContentType.SIMPLE_TEXT.equals(emailBody.getEmailContentType())) {
                    throw new BlogException("-EMAIL_SEND_001", "需要调用setEmailContentType选择email邮件模板");
                }
                //获取默认文件
                String htmlTemplete = emailBody.readEmailHtmlTemplete();
                //注入参数
                String formatHtmlMsg = MessageFormat.format(htmlTemplete, contentDatasource.getParamSet().toArray());
                htmlEmail.setHtmlMsg(formatHtmlMsg);
            } else {
                if (!EmailContentType.SIMPLE_TEXT.equals(emailBody.getEmailContentType())) {
                    log.warn("没有正确的调用setEmailContentType选择email邮件模板，已自动修正参数");
                    emailBody.setEmailContentType(EmailContentType.SIMPLE_TEXT);
                }
                htmlEmail.setMsg(String.valueOf(contentDatasource.getEmailContent().get(EmailContentDatasource.CONTENT_KEY)));
            }
            //附件内容,强制使用Url本项目
            List<JSONObject> attachmentFiles = emailBody.getAttachmentFiles();
            if (!attachmentFiles.isEmpty()) {
                attachmentFiles.forEach(fileItem -> {
                    try {
                        htmlEmail.attach(new URL(fileItem.getString("url")), fileItem.getString("name"), EmailAttachment.ATTACHMENT);
                    } catch (EmailException e) {
                        throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
                    } catch (MalformedURLException e) {
                        throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
                    }
                });
            }
            String send = htmlEmail.send();
            log.info("邮件状态：{}", send);
            return send;
        } catch (EmailException e) {
            throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
        } catch (Exception e) {
            throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
        }
    }

    /**
     * 发送邮件信息体的参数校验
     *
     * @param emailBody 邮件信息体对象
     * @return 返回参数是否校验成功
     */
    private boolean paramsCheck(EmailBody emailBody) {
        if (StringUtils.isEmpty(emailBody.getSubject())) {
            throw new BlogException("-EMAIL_SEND_001", "邮件标题不能为空！");
        }
        if (StringUtils.isEmpty(emailBody.getContentDatasource().getEmailContent().get(EmailContentDatasource.CONTENT_KEY))) {
            throw new BlogException("-EMAIL_SEND_001", "邮件内容不能为空！");
        }
        if (StringUtils.isEmpty(emailBody.getToEmails())) {
            throw new BlogException("-EMAIL_SEND_001", "邮件接收对象不能为空！");
        }
        return true;
    }


}
