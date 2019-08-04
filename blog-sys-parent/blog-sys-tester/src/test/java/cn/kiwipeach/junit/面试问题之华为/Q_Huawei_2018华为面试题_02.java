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
package cn.kiwipeach.junit.面试问题之华为;

import org.junit.After;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 描述：华为2018面试器
 *
 * @author kiwipeach
 * @create 2019-07-26
 */
public class Q_Huawei_2018华为面试题_02 {

    /**
     * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，他先用计算机生成了N个1到1000之间的随机整数（N≤1000），对于其中
     * 重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。然后再把这些数从小到大排序，按照排好的顺序去找
     * 同学做调查。请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
     * Input Param
     * n               输入随机数的个数
     * inputArray      n个随机整数组成的数组
     * Return Value
     * OutputArray    输出处理后的随机整数
     * 注：测试用例保证输入参数的正确性，答题者无需验证。测试用例不止一组。
     * 输入描述:
     * 输入多行，先输入随机整数的个数，再输入相应个数的整数
     * 输出描述:
     * 返回多行，处理后的结果
     * 输入例子1:
     * 11
     * 10
     * 20
     * 40
     * 32
     * 67
     * 40
     * 20
     * 89
     * 300
     * 400
     * 15
     * 输出例子1:
     * 10
     * 15
     * 20
     * 32
     * 40
     * 67
     * 89
     * 300
     * 400
     */
    public static void main(String[] args) {
        //数组去重排序问题
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入随机数的个数：");
        int n = scanner.nextInt();
        //定义
        List<Integer> inputLinkedSet = new LinkedList<>();
        Set<Integer> noRepeatHashSet = new HashSet<>();
        Set<Integer> sortedSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2 > 0 ? 1 : o1 == o2 ? 0 : -1;
            }
        });
        //去重
        for (int i = 0; i < n; i++) {
            int nextInput = scanner.nextInt();
            inputLinkedSet.add(nextInput);
            noRepeatHashSet.add(nextInput);
        }
        //排序
        noRepeatHashSet.forEach(
                num -> {
                    sortedSet.add(num);
                }
        );
        //输出
        System.out.println("输入参数：" + inputLinkedSet);
        System.out.println("去重结果：" + noRepeatHashSet);
        System.out.println("排序结果：" + sortedSet);

    }


    @Test
    public void 排序结合() {
        List<Integer> integers = Arrays.asList(32, 400, 67, 20, 40, 89, 10, 11, 300, 15);
        Set<Integer> sortedSet = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                //if (o1 > o2) {
                //    return 1;
                //} else if (o1 == o2) {
                //    return 0;
                //} else {
                //    return -1;
                //}
                return o1 - o2 > 0 ? 1 : o1 == o2 ? 0 : -1;
            }
        });

        integers.forEach(
                n -> {
                    sortedSet.add(n);
                }
        );
        System.out.println(sortedSet);
    }

    @Test
    public void 数组是地址引用() {
        int arr[] = new int[]{1, 2, 3};
        changeArray(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }

        //声明数组
        int bookNums[] = new int[10];
        System.out.println(bookNums[0]==0);
        String strs[] = new String[10];
        System.out.println(bookNums[0]==0);
        System.out.println("".equals(strs[0]));
        System.out.println(strs[0]==null);
        System.out.println(null!=null);

        //String s = "hello";int i=3;s+=i;
        //String s = "hello";int i=3;s=i+s;

    }

    private void changeArray(int[] arr) {
        arr[0] = 4;
    }

    @Test
    public void 线程可以同时继承和实现接口() throws InterruptedException {
        //new Thread(new MyTaskThread()).start();
        new MyTaskThread().start();
        Thread.sleep(3000);
        Double a = 1.0;
        byte b = 127;
    }


    class MyTaskThread extends Thread implements Runnable {
        @Override
        public void run() {
            System.out.println("Test");
        }
    }


    /*
    * 子类只能扩大或者相等父类的修饰符，不能缩小范围。
    * */
    class Father {
        protected void smoke(){};
    }

    class Son extends Father{
        public void smoke() {
            super.smoke();
        }

        public void add(int a){}

        public int add(int a,float b){return 1;}

        protected void add(int a,int b){}
    }


    @Test
    public void 测试哈希Set(){
        Set<A> set = new HashSet<>();
        set.add(new A());
        set.add(new A());
        set.add(new A());
        System.out.println(set.size());
        A a1 = new A();
        A a2 = new A();
        System.out.println(a1.equals(a2));
        System.out.println(a1==a2);


    }

    class A{
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return true;
        }

    }


}
