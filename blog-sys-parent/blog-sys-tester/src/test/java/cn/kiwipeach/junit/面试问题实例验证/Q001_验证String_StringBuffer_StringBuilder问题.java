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
package cn.kiwipeach.junit.面试问题实例验证;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：证明StringBuffer线程安全，StringBuilder线程不安全
 *
 * @author kiwipeach
 * @create 2019-07-25
 */
@Slf4j
public class Q001_验证String_StringBuffer_StringBuilder问题 {

    @Setter
    class StringTask implements Runnable {
        //共享变量
        private StringBuilder stringBuilder;
        private StringBuffer stringBuffer;

        public StringTask(StringBuilder stringBuilder, StringBuffer stringBuffer) {
            this.stringBuilder = stringBuilder;
            this.stringBuffer = stringBuffer;
        }

        @Override
        public void run() {
            try {
                //让线程并发变明显
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stringBuilder.append("A");
            stringBuffer.append("A");
        }
    }

    @Test
    public void 证明StringBuffer和StringBuilder的线程安全性质() throws InterruptedException {
        //十个线程，每个线程追加10次
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder stringBuilder = new StringBuilder();
        StringTask stringTask = new StringTask(stringBuilder, stringBuffer);
        for (int i = 0; i < 10; i++) {
            new Thread(stringTask).start();
        }
        Thread.sleep(3000);
        log.warn("StringBuffer Size is {}，StringBuilder size is {}", stringBuffer.length(), stringBuilder.length());
    }

    @Test
    public void 非常简洁的代码证明StringBuilder不安全() throws InterruptedException {
        StringBuilder stringBuilder = new StringBuilder();
        //定义任务
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stringBuilder.append("A");
            }
        };
        //构造线程
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(task));
        }
        //启动线程
        threads.forEach(thread -> {
            thread.start();
        });
        //等待线程执行完毕
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        //线程不安全，长度输出结果会有小于10的情况
        log.warn("StringBuilder size is {}", stringBuilder.length());
    }


}
