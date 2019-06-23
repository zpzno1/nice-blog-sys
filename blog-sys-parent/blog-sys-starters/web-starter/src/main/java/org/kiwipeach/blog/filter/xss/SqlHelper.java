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
import org.springframework.util.StringUtils;

/**
 * 描述：sql过滤辅助类
 *
 * @author kiwipeach
 * @create 2019-06-24
 */
@Slf4j
public class SqlHelper {
    private static final String sqlInjectParams= "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
            "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
            "table|from|grant|use|group_concat|column_name|" +
            "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
            "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";

    public static String sqlEncode(String value) {
        String paramValue = value;
        for (String keyword : sqlInjectParams.split("\\|")) {
            if (value.contains(keyword)) {
                log.warn("系统检测到疑似非法sql攻击:notallow={},value={}",keyword,value);
                throw new BlogException("-SQL_001", "系统检测到您正在进行非法的xss攻击，如果系统判断有误，请联系统管理员。");
            }
        }
        return paramValue;
    }

    public static void main(String[] args) {
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|,|like|//|/|%|#";
        for (String keyword : badStr.split("\\|")) {
            System.out.println(keyword);
        }
    }

}
