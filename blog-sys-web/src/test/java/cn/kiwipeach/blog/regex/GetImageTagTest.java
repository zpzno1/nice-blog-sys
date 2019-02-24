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
package cn.kiwipeach.blog.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取图片地址正则测试
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
public class GetImageTagTest {
    String targetContent = "# 1.ajax中context可以指定请求执行的上下文\n" +
            "\n" +
            "```javascript\n" +
            "//如果不设定这个参数，那么this就指向调用本次AJAX请求时传递的options参数\n" +
            "$.ajax({ url: \"test.html\", context: document.body, success: function(){\n" +
            "    $(this).addClass(\"done\");\n" +
            "}});\n" +
            "```\n" +
            "\n" +
            "# 2.爬虫网站有robots.txt协议的内容不允许爬取\n" +
            "\n" +
            "# ![1546672864294](assets/1546672864294.png)\n" +
            "\n" +
            "![开发语录-idea动态酷炫logo-2019223123242](http://cdn.kiwipeach.cn/开发语录-idea动态酷炫logo-2019223123242.png)";

    @Test
    public void testReg1() {
        String content = "I am noob from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, targetContent);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }

    @Test
    public void testReg2() {

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    @Test
    public void testReg3() {
        String target = "![开发语录-idea动态酷炫logo-2019223123242](http://cdn.kiwipeach.cn/开发语录-idea动态酷炫logo-2019223123242.png)";
        if (target.startsWith("![") && target.endsWith(".png)")) {
            int begin = target.indexOf("(http://");
            int end = target.lastIndexOf(")");
            System.out.println(target.substring(begin+1,end));
        }
    }
}
