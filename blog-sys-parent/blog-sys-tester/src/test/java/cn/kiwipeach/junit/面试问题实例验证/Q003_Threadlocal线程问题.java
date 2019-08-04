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

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.search.aggregator.Count;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：线程副本变量，使用场景：数据源连接对象，应该每个线程的connection互不干扰，并且关闭该连接也互不影响
 *
 * @author kiwipeach
 * @create 2019-07-25
 */
@Getter
class CountObjcet {
    int count = 0;
    private ThreadLocal<Integer> threadLocalCount = new ThreadLocal<Integer>();

    public void makePlus() {
        count++;
    }

    public void threadMakePlus() {
        threadLocalCount.set(threadLocalCount.get() + 1);
    }

    /**
     * 设置当前线程变量
     *
     * @param value 当前线程变量值
     */
    public void setThreadLocal(Integer value) {
        threadLocalCount.set(value);
    }
}

@Getter
class CountObjectPlusTask implements Runnable {
    private CountObjcet countObjcet;

    public CountObjectPlusTask(CountObjcet countObjcet) {
        this.countObjcet = countObjcet;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            //普通变量+1操作
            countObjcet.makePlus();
            //ThreadLocal变量+1操作
            countObjcet.setThreadLocal(0);
            countObjcet.threadMakePlus();
        }
    }
}

@Slf4j
public class Q003_Threadlocal线程问题 {

    @Test
    public void 线程访问结果等于10个线程的累积之和() {
        CountObjcet count = new CountObjcet();
        count.setThreadLocal(0);
        CountObjectPlusTask countObjectPlusTask = new CountObjectPlusTask(count);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(countObjectPlusTask));
        }
        threads.forEach(thread -> {
            thread.start();
        });
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("程序执行完毕：no thread local count={}", count.getCount());    //result = 10
        log.info("程序执行完毕：thread local count={}", count.getThreadLocalCount().get());//result = 0
    }
}
