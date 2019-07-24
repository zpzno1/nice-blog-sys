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
package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.email.EmailBody;

/**
 * 描述：邮件发送相关服务类
 *
 * @author kiwipeach
 * @create 2019-07-23
 */
public interface IEmailService {

    /**
     * 发送邮件信息
     *
     * @param emailBody 邮件信息体
     * @return 是否发送成功
     */
    boolean sendEmail(EmailBody emailBody);

}
