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
package cn.kiwipeach.blog.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-07-25
 */
public class 线程池测试 {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 100, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));
        for (int i = 0; i < 10; i++) {
            executor.execute(new MyTask(i));
            System.out.println("线程池中线程数：" + executor.getPoolSize() + "，队列中等待执行的任务数：" +
                    executor.getQueue().size() + "，已执行完的任务数：" + executor.getCompletedTaskCount());
        }
    }
    static class MyTask implements Runnable {

        private int id;

        public MyTask(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("开始执行：任务 " + id);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行完毕：任务 " + id);
        }

    }
}
