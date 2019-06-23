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
package org.kiwipeach.blog.filter.xss;

import cn.kiwipeach.blog.exception.BlogException;
import lombok.extern.slf4j.Slf4j;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.regex.Pattern;

/**
 * 描述：xss辅助工具类
 *
 * @author kiwipeach
 * @create 2019-06-24
 */
@Slf4j
public class XssHelper {
    /**
     * 对一些特殊字符进行转义
     */
    public static String HTMLEncode(String aText) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(aText);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {
            if (character == '<') {
                result.append("＜");
            } else if (character == '>') {
                result.append("＞");
            } else if (character == '\"') {
                result.append("｀");
            } else {
                // the char is not a special one
                // add it to the result as is
                result.append(character);
            }
            character = iterator.next();
        }
        return result.toString();
    }

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     * 目前xssProject对注入代码要求是必须开始标签和结束标签(如<script></script>)正确匹配才能解析，否则报错；因此只能替换调xssProject换为自定义实现
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        String result = stripXSS(s);
        if (null != result) {
            result = escape(result);
        }

        return result;

    }

    private static String escape(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append('＞');// 全角大于号
                    break;
                case '<':
                    sb.append('＜');// 全角小于号
                    break;
                case '\'':
                    sb.append('‘');// 全角单引号
                    break;
                case '\"':
                    sb.append('“');// 全角双引号
                    break;
                case '\\':
                    sb.append('＼');// 全角斜线
                    break;
                case '%':
                    sb.append('％'); // 全角冒号
                    break;
                default:
                    sb.append(c);
                    break;
            }

        }
        return sb.toString();
    }

    private static String stripXSS(String value) {
        if (value != null) {
            // NOTE: It's highly recommended to use the ESAPI library and
            // uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);
            // Avoid null characters
            value = value.replaceAll("", "");
            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);

            value = scriptPattern.matcher(value).replaceAll("");
            // Avoid eval(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid expression(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("<iframe>(.*?)</iframe>", Pattern.CASE_INSENSITIVE);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");


            scriptPattern = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<iframe(.*?)>",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            scriptPatternExceptionCheck(scriptPattern, value);
            value = scriptPattern.matcher(value).replaceAll("");
        }
        return value;
    }

    private static void scriptPatternExceptionCheck(Pattern scriptPattern, String value) {
        if (scriptPattern.matcher(value).find()) {
            log.warn("系统检测到疑似非法xss攻击:scriptPattern={},value={}", scriptPattern.pattern(), value);
            throw new BlogException("-XSS_001", "系统检测到您正在进行非法的xss攻击，如果系统判断有误，请联系统管理员。");
        }
    }

}
