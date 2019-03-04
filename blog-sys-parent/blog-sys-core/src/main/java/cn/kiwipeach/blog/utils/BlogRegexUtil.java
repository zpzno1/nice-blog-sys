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
package cn.kiwipeach.blog.utils;

import java.util.regex.Pattern;

/**
 * 博客正则分析工具类
 *
 * @author kiwipeach
 * @create 2019-03-04
 */
public class BlogRegexUtil {
    private static final int DEFAULT_INTRODUCTION_LEN = 50;
    /**
     * 中文+英文+数字+特殊字符的正则表达式是
     */
    private static final String patterns[] = new String[]{
            "[\\u4E00-\\u9FA5]",
            "[a-zA-Z]",
            "[0-9]",
            "[!@$%^&()+=|{}':;',\\[\\]./?￥%……&（）+|{}【】‘；：”“’。，、？]"
    };

    /**
     * 分析博客markdown语法，并生成博客简介
     *
     * @param markdown 博客语法
     * @return 返回博客内容简介信息
     */
    public static String analyseMarkdown(String markdown) {
        StringBuffer sb = new StringBuffer();
        char[] chars = markdown.toCharArray();
        int count = 0;
        for (char ch : chars) {
            for (String pattern : patterns) {
                if (Pattern.matches(pattern, String.valueOf(ch))) {
                    sb.append(ch);
                    count++;
                    break;
                }
            }
            if (count == DEFAULT_INTRODUCTION_LEN) {
                break;
            }
        }
        return sb.toString();
    }

}
