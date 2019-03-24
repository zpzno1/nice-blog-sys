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
package cn.kiwipeach.junit.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kiwipeach
 * @create 2019-03-22
 */
@Slf4j
public class 攻击某个不知名的钓鱼网站 {


    @Test
    public void postAttrack() throws IOException, InterruptedException {
        int corePoolSize = 15;
        int maximumPoolSize = 20;
        long keepAliveTime = 20;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(30);
        //ThreadFactory threadFactory = new NameTreadFactory();
        //RejectedExecutionHandler handler = new MyIgnorePolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit,
                workQueue);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 1; i <= 20; i++) {
            MyThread task = new MyThread();
            executor.execute(task);
        }
        System.in.read();
    }

    @Test
    public void testSStaticMethod() {
        Map map = new HashMap();
        map.put("name", "kiwipeach");
        map.put("email", "1099501218@qq.com");

        System.out.println(com.alibaba.fastjson.JSON.toJSONString(map));
    }
}
