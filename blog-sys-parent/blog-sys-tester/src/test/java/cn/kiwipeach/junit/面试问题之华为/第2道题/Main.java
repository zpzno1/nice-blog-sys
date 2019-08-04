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
package cn.kiwipeach.junit.面试问题之华为.第2道题;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-07-28
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = br.readLine()) != null) {
            String result = reverString(userInput);
            System.out.println(result);
        }

    }

    private static String reverString(String userInput) {
        Stack<String> stack = new Stack<>();
        StringBuilder sbTemp = new StringBuilder();
        int len = userInput.length();
        int count = 0;
        for (char ch : userInput.toCharArray()) {
            if (isChar(ch)) {
                sbTemp.append(ch);
                //如果为特殊字符，那么就收集字符串后将收集字符串压栈
                int nextIndex = count + 1 == len ? len - 1 : count + 1;
                if (!isChar(userInput.charAt(nextIndex)) || count + 1 == len) {
                    stack.push(sbTemp.toString());
                    sbTemp = new StringBuilder();
                }
            } else {
                //特殊字符串直接空格处理
                stack.push(" ");
            }
            count++;
        }
        StringBuilder resultSb = new StringBuilder();
        while (!stack.isEmpty()) {
            resultSb.append(stack.pop());
        }
        return resultSb.toString();
    }

    private static boolean isChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}
