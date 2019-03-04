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
            System.out.println(target.substring(begin + 1, end));
        }
    }

    @Test
    public void 测试中文获取() {
        //String strE = "hello world asd 数据计算技术解决  计算机数据计算  123asd  asds123";
        //String strE = "hello中国";
        String pattern1 = "[\\u4E00-\\u9FA5]";//中文正则
        String pattern2 = "[a-zA-Z]";//英文
        String pattern3 = "[0-9]";//数字
        String pattern4 = "[!@$%^&()+=|{}':;',\\[\\]./?￥%……&（）+|{}【】‘；：”“’。，、？]";
        String patterns[] = new String[]{pattern1, pattern2, pattern3, pattern4};
        //String[] splitStr = strE.split(" ");
        //for(String str:splitStr) {
        //    if(Pattern.matches(pattern, str))
        //        System.out.println(str);
        //}

        String content = "# 欢迎使用Markdown编辑器写博客\n" +
                "\n" +
                "本Markdown编辑器使用[StackEdit][6]修改而来，用它写博客，将会带来全新的体验哦：\n" +
                "\n" +
                "- **Markdown和扩展Markdown简洁的语法**\n" +
                "- **代码块高亮**\n" +
                "- **图片链接和图片上传**\n" +
                "- ***LaTex*数学公式**\n" +
                "- **UML序列图和流程图**\n" +
                "- **离线写博客**\n" +
                "- **导入导出Markdown文件**\n" +
                "- **丰富的快捷键**\n" +
                "\n" +
                "-------------------\n" +
                "\n" +
                "## 快捷键\n" +
                "\n" +
                " - 加粗    `Ctrl + B` ";
        char[] contents = content.toCharArray();
        for (char str : contents) {
            for (String pattern : patterns) {
                if (Pattern.matches(pattern, String.valueOf(str)))
                    System.out.print(str);
            }
        }
        System.out.println();
    }
}
