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
package cn.kiwipeach.blog.enums;

/**
 * <code,value> 类型的枚举，用来存放系统开发时候出现的编码
 *
 * @author kiwipeach
 * @create 2019-01-30
 */
public enum EmailContentType {
    //SIMPLE_TEXT(简单文本类型)，ATTACHMENT_FILE(带附件邮件类型)，MESSAGE_FORMAT_OF_XXX(格式化消息类型，带有模板配置路径和参数个数信息)
    SIMPLE_TEXT("", 0),
    MESSAGE_FORMAT_OF_BLOG_COMMENT_REPLY("/email/blog_comment_reply.html", 3),
    MESSAGE_FORMAT_OF_SITE_STATISTICS("/email/site_statistics.html", 6),
    ;

    /**
     * 模板文件路径信息
     */
    private String contentTemplete;

    /**
     * 占位符个数
     */
    private int paramCount;

    EmailContentType(String contentTemplete, int paramCount) {
        this.contentTemplete = contentTemplete;
        this.paramCount = paramCount;
    }

    public String getContentTemplete() {
        return contentTemplete;
    }

    public int getParamCount() {
        return paramCount;
    }
}
