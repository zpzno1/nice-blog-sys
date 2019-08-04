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

import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * 描述：链表操作
 *
 * @author kiwipeach
 * @create 2019-07-29
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    public ListNode addNode(ListNode node) {
        return null;
    }

    public ListNode beforeInsertNode(int index,ListNode node) {
        return null;
    }

    public ListNode afterInsertNode(int index,ListNode node) {
        return null;
    }

    public ListNode deleteNode(int index) {
        return null;
    }
    public ListNode updateNode(int index) {
        return null;
    }

}

public class Q005链表练习 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer userInput;
        while ((userInput = Integer.parseInt(br.readLine())) != null) {
            System.out.println(GetSequeOddNum(userInput));
        }
    }

    public static String GetSequeOddNum(int oddNum) {
        int total = oddNum * oddNum * oddNum;
        int midNum = total / oddNum;
        List<Integer> cal = new ArrayList<Integer>();
        int leftNum;
        int rightNum;
        int time;
        if (oddNum % 2 == 0) {
            leftNum = midNum - 1;
            rightNum = midNum + 1;
            time = oddNum;
        } else {
            leftNum = midNum;
            rightNum = midNum;
            time = oddNum - 1;
            cal.add(midNum);
        }
        for (int i = 0; i < time / 2; i++) {
            cal.add(leftNum - 2);
            cal.add(rightNum + 2);
        }
        StringBuilder sb = new StringBuilder();
        for (int t : cal) {
            sb.append(t).append("+");
        }
        return sb.substring(0, sb.lastIndexOf("+"));
    }

}
