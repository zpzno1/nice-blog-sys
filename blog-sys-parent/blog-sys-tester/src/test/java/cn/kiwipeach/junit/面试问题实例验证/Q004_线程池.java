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
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * 描述：线程池创建核心类
 * <p>
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue) {});
 *
 * @author kiwipeach
 * @create 2019-07-25
 */

@Slf4j
public class Q004_线程池 {

    @After
    public void after() throws InterruptedException {
        //阻塞单元测试类线程
        Thread.sleep(200000);
    }

    /*
     * 特点：能无限制地添加线程任务，空闲60s后被销毁，将新任务交给空闲线程执行，如果没有空闲线程，则创建线程。
     * 缺点：提交线程无限制，容易造成系统资源不足。
     * 构造：new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>())
     */
    @Test
    public void newCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    /*
     * 特点：创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     * 缺点：核心线程和最大线程数相同，线程永长时间不工作也不停止.
     * 构造：new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
     * */
    @Test
    public void newFixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /*
     * 构造：new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new DelayedWorkQueue())
     * */
    @Test
    public void newScheduledThreadPool() {
        //第一种：延迟三秒后执行一次
        /*ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);*/

        //第二种：1s后，没三秒执行一次线程
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        }, 1, 3, TimeUnit.SECONDS);
    }

    /*
     * 构造：new FinalizableDelegatedExecutorService (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()))
     * */
    @Test
    public void newSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                public void run() {
                    try {
                        System.out.println(index);
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    @Test
    public void 自定义线程池() throws InterruptedException {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        //指定一种队列 （有界队列 先进先出）
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);
        //丢弃最老的一个任务，执行新的任务
        //ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, 30, TimeUnit.SECONDS, workQueue, new MyCustomDiscardOldestPolicy());
        MyCount myCount = new MyCount();
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(new MyTask("[线程-" + i + "]", myCount));
            log.info("活跃线程数：{}", threadPoolExecutor.getActiveCount());
        }

        //再次添加10个线程观察情况
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(new MyTask("[收尾线程-" + i + "]", myCount));
        }
        /*
        * console result:
            01:08:39.964  - 活跃线程数：1
            01:08:39.967  - 活跃线程数：2
            01:08:39.967  - 活跃线程数：3
            01:08:39.968  - 活跃线程数：4
            01:08:39.968  - 活跃线程数：5  (maximumPoolSize=5，此时会将后面进来的线程进入ArrayBlockingQueue队列)
            01:08:39.968  - 活跃线程数：5
            01:08:39.968  - 活跃线程数：5
            01:08:39.968  - 活跃线程数：5 （队列大小为3所以只有三个进去了）
            01:08:39.968  - 活跃线程数：6
            01:08:39.968  - 活跃线程数：7
            01:08:39.968  - 活跃线程数：8
            01:08:39.968  - 活跃线程数：9
            01:08:39.968  - 活跃线程数：10 （此时达到最大的线程池总数，需要对过多的其他人进程采取策略进行移除和添加）
            01:08:39.968  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-5]', data=0}, MyTask{name='[线程-6]', data=0}, MyTask{name='[线程-7]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-5],新的入队任务：[线程-13]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-6]', data=0}, MyTask{name='[线程-7]', data=0}, MyTask{name='[线程-13]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-6],新的入队任务：[线程-14]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-7]', data=0}, MyTask{name='[线程-13]', data=0}, MyTask{name='[线程-14]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-7],新的入队任务：[线程-15]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-13]', data=0}, MyTask{name='[线程-14]', data=0}, MyTask{name='[线程-15]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-13],新的入队任务：[线程-16]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-14]', data=0}, MyTask{name='[线程-15]', data=0}, MyTask{name='[线程-16]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-14],新的入队任务：[线程-17]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-15]', data=0}, MyTask{name='[线程-16]', data=0}, MyTask{name='[线程-17]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-15],新的入队任务：[线程-18]
            01:08:39.969  - 活跃线程数：10
            01:08:39.969  - 自定义拒绝策略执行,当前队列：[MyTask{name='[线程-16]', data=0}, MyTask{name='[线程-17]', data=0}, MyTask{name='[线程-18]', data=0}]
            01:08:39.969  - 拒绝任务：[线程-16],新的入队任务：[线程-19]
            01:08:39.969  - 活跃线程数：10
            01:08:40.964 [pool-1-thread-1] INFO   - 线程（[线程-0]）打印：1
            01:08:41.964 [pool-1-thread-1] INFO   - 线程（[线程-17]）打印：2
            01:08:42.964 [pool-1-thread-1] INFO   - 线程（[线程-18]）打印：3
            01:08:43.965 [pool-1-thread-1] INFO   - 线程（[线程-19]）打印：4
            01:08:44.966 [pool-1-thread-2] INFO   - 线程（[线程-1]）打印：5
            01:08:45.966 [pool-1-thread-3] INFO   - 线程（[线程-2]）打印：6
            01:08:46.967 [pool-1-thread-4] INFO   - 线程（[线程-3]）打印：7
            01:08:47.967 [pool-1-thread-5] INFO   - 线程（[线程-4]）打印：8
            01:08:48.967 [pool-1-thread-6] INFO   - 线程（[线程-8]）打印：9
            01:08:49.967 [pool-1-thread-7] INFO   - 线程（[线程-9]）打印：10
            01:08:50.968 [pool-1-thread-8] INFO   - 线程（[线程-10]）打印：11
            01:08:51.969 [pool-1-thread-10] INFO   - 线程（[线程-12]）打印：12
            01:08:52.970 [pool-1-thread-9] INFO   - 线程（[线程-11]）打印：13
        * */
    }

    /*自定义数据*/
    @Getter
    @Setter
    class MyCount {
        //锁对象
        private Lock lock = new ReentrantLock();
        private int data = 0;

        public void plus() {
            data++;
        }
    }

    /*
     * 自定义线程
     * */
    @Getter
    class MyTask implements Runnable {
        private String name;
        private MyCount data;

        public MyTask(String name, MyCount data) {
            this.name = name;
            this.data = data;
        }

        @Override
        public void run() {
            this.data.getLock().lock();
            try {
                Thread.sleep(1000);//为了模拟高并发
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.plus();
            log.info("线程（{}）打印：{}", this.name, data.getData());
            this.data.getLock().unlock();
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", data=" + data.getData() +
                    '}';
        }
    }

    /*
     * 自定义拒绝策略
     * */
    public class MyCustomDiscardOldestPolicy implements RejectedExecutionHandler {
        /**
         * 将先入队列的进程移除掉
         *
         * @param r the runnable task requested to be executed
         * @param e the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            log.info("自定义拒绝策略执行,当前队列：{}", e.getQueue());
            if (!e.isShutdown() && r instanceof MyTask) {
                MyTask removeTask = (MyTask) e.getQueue().poll();
                MyTask nextTask = (MyTask) r;
                log.info("拒绝任务：{},新的入队任务：{}", removeTask.getName(), nextTask.getName());
                e.execute(r);
            }
        }
    }


}
