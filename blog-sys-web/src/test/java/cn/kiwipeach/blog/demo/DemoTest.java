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

import cn.kiwipeach.blog.enums.BlogSys;
import cn.kiwipeach.blog.enums.CodeValueEnum;
import cn.kiwipeach.blog.exception.BlogException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author kiwipeach
 * @create 2019-01-26
 */
@Slf4j
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
        BlogSys platform = BlogSys.QQ;
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


    @Test
    public void 获取格式化时间() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        log.info("{}年{}月{}日 {}时{}分{}秒 星期{}", now.getYear(), now.getMonth(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getDayOfWeek());
    }

    @Test
    public void 测试线程是否会影响返回值() throws InterruptedException {
        try {
            System.out.println(threadReturnValue());
        } catch (Exception e) {
            log.info("异常");
        }
        //阻塞程序
        Thread.sleep(400000);
    }

    public String threadReturnValue() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    log.info("线程开始");
                    Thread.sleep(3000);
                    if (true) throw new BlogException("-fdsafds","消息测试");
                    log.info("线程结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return "kiwipeach";
    }

}
