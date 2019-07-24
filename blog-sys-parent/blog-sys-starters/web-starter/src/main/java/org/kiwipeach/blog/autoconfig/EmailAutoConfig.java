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
package org.kiwipeach.blog.autoconfig;

import cn.kiwipeach.blog.email.EmailConfig;
import cn.kiwipeach.blog.exception.BlogException;
import org.apache.commons.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Authenticator;

/**
 * 描述：邮件配置服务类
 *
 * @author kiwipeach
 * @create 2019-07-23
 */
@Configuration
public class EmailAutoConfig {

    @Autowired
    EmailConfig emailConfig;

    /**
     * 鉴权bean
     */
    @Bean
    public Authenticator authenticatorBean() {
        return new DefaultAuthenticator(emailConfig.getUserName(), emailConfig.getPassword());
    }

    /**
     * 简单邮件发送对象
     */
    //@Bean
    //public Email simpleEmail(Authenticator authenticator) {
    //    return setNeedEmailAttribute(authenticator, new SimpleEmail());
    //}

    /**
     * 多附件邮件发送对象
     */
    //@Bean
    //public Email multiPartEmail(Authenticator authenticator) {
    //    return setNeedEmailAttribute(authenticator, new MultiPartEmail());
    //}

    /**
     * 带html代码的邮件发送对象
     */
    @Bean
    public HtmlEmail htmlEmail(Authenticator authenticator) {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setSSLOnConnect(emailConfig.isSslOnConnect());
        htmlEmail.setHostName(emailConfig.getHost());
        htmlEmail.setSmtpPort(emailConfig.getPort());
        htmlEmail.setAuthenticator(authenticator);
        htmlEmail.setCharset("UTF-8");
        try {
            htmlEmail.setFrom(emailConfig.getUserName());
        } catch (EmailException e) {
            throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
        }
        return htmlEmail;
        //return setNeedEmailAttribute(authenticator, new HtmlEmail());
    }

    /**
     * 设置email必须属性
     *
     * @param authenticator 鉴权对象
     * @param email         邮件对象
     * @return 返回eamil
     */
    //private Email setNeedEmailAttribute(Authenticator authenticator, Email email) {
    //    email.setSSLOnConnect(emailConfig.isSslOnConnect());
    //    email.setHostName(emailConfig.getHost());
    //    email.setSmtpPort(emailConfig.getPort());
    //    email.setAuthenticator(authenticator);
    //    try {
    //        email.setFrom(emailConfig.getUserName());
    //    } catch (EmailException e) {
    //        throw new BlogException("-EMAIL_SEND_001", e.getLocalizedMessage());
    //    }
    //    return email;
//}


}
