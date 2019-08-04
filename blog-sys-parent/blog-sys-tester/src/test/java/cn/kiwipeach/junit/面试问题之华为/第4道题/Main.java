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
package cn.kiwipeach.junit.面试问题之华为.第4道题;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-07-29
 */

import org.junit.Test;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        int pushA[] = new int[]{1};
        //int popA[] = new int[]{4, 5, 3, 2, 1};
        int popA[] = new int[]{1};
        System.out.println(IsPopOrder(pushA, popA));
    }

    public static boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 1 && popA.length == 1) {
            return true;
        }
        Stack<Integer> pushStack = new Stack<Integer>();
        Stack<Integer> popStack = new Stack<Integer>();
        //初始化popStack
        for (int i = popA.length - 1; i >= 0; i--) {
            popStack.push(popA[i]);
        }
        for (int cur : pushA) {
            pushStack.push(cur);
            if (pushStack.peek() == popStack.peek()) {
                //弹出栈顶元素
                popStack.pop();
                pushStack.pop();
                //继续比较两个栈的栈顶元素，直到不相等则退出
                if (pushStack.isEmpty() || popStack.isEmpty()) {
                    break;
                }
                while (popStack.peek() == pushStack.peek()) {
                    popStack.pop();
                    pushStack.pop();
                    if (pushStack.isEmpty() || popStack.isEmpty()) {
                        break;
                    }
                }
            }
        }
        return popStack.size() == 0 ? true : false;
    }

    @Test
    public void 栈测试() {
        Stack<Integer> pushStack = new Stack<Integer>();
        System.out.println(pushStack.isEmpty());
    }
}
