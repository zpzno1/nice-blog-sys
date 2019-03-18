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
package cn.kiwipeach.blog.demo;

import cn.kiwipeach.blog.enums.CodeEnum;
import cn.kiwipeach.blog.enums.CodeValueEnum;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * @author kiwipeach
 * @create 2019-01-26
 */
public class DemoTest {

    public class ApplicationPersonalBean {

        private String HEAD_URL;//头像下载地址
        private String AAC003;//姓名
        private String AAC004NAME;//性别

        public String getHEAD_URL() {
            return HEAD_URL;
        }

        public void setHEAD_URL(String HEAD_URL) {
            this.HEAD_URL = HEAD_URL;
        }

        public String getAAC003() {
            return AAC003;
        }

        public void setAAC003(String AAC003) {
            this.AAC003 = AAC003;
        }

        public String getAAC004NAME() {
            return AAC004NAME;
        }

        public void setAAC004NAME(String AAC004NAME) {
            this.AAC004NAME = AAC004NAME;
        }
    }

    @Test
    public void test() {
        ApplicationPersonalBean bean = new ApplicationPersonalBean();
        bean.setAAC003("刘卜铷");
        System.out.println(JSON.toJSONString(bean));
    }

    @Test
    public void testEnum() {
        CodeEnum platform = CodeEnum.QQ;
        System.out.println(platform);
        System.out.println(platform.toString().equals("qq"));

        CodeValueEnum sex = CodeValueEnum.GIRL;
        System.out.println(sex);
    }

    @Test
    public void testNewLine() {
        String hello = "Hello ";
        String world = "World";
        System.out.println("-----");
        System.out.print(System.getProperty("line.separator"));
        System.out.println("-----");
        System.out.println(hello + "\n" + world);
    }

    @Test
    public void testSwitchCase() {
        System.out.println(caseMethod("fdsadf"));
    }

    public String caseMethod(String key) {
        switch (key) {
            case "ok":
                System.out.println("xxx");
                return "确定";
            case "cancel":
                System.out.println("yyy");
                return "取消";
            default:
                System.out.println("zzz");
                return "desc";
        }
    }

}
