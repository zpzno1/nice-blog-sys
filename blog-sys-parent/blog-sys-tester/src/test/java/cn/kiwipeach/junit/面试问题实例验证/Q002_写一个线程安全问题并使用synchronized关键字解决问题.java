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

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述：证明StringBuffer线程安全，StringBuilder线程不安全
 *
 * @author kiwipeach
 * @create 2019-07-25
 */
class CountPlusTask implements Runnable {
    /**
     * 共享变量
     */
    private int count = 0;
    /**
     * 同步代码块，同步参照对象
     */
    private Object obj = new Object();
    /**
     * 锁对象
     */
    private Lock lock = new ReentrantLock();

    public void countPlus() {
        count++;
        System.out.println("线程" + Thread.currentThread().getName() + "，处理结果:" + count);
    }

    public synchronized void countPlusSafe1() {
        synchronized (obj) {
            count++;
            System.out.println("线程" + Thread.currentThread().getName() + "，处理结果:" + count);
        }
    }


    @Override
    public void run() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程不安全
        //countPlus();

        //方法一：线程安全,使用同步代码块
        //synchronized (obj) {
        //    count++;
        //    System.out.println(Thread.currentThread().getName() + "，处理结果:" + count);
        //}

        //方法二：线程安全，使用同步方法
        //countPlusSafe1();

        //方法三：使用lock
        lock.lock();
        count++;
        System.out.println(Thread.currentThread().getName() + "，处理结果:" + count);
        lock.unlock();

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}

@Slf4j
public class Q002_写一个线程安全问题并使用synchronized关键字解决问题 {


    @Test
    public void 测试线程安全问题() throws InterruptedException {
        //用一百个线程，将CountPlusTask中的count加到100
        CountPlusTask countPlusTask = new CountPlusTask();
        for (int i = 0; i < 10; i++) {
            new Thread(countPlusTask, "线程君-" + i).start();
        }
        Thread.sleep(3000);
        System.out.println(countPlusTask.getCount());
    }

}


