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
package cn.kiwipeach.junit.面试问题之华为.第5道题;

/**
 * 描述：fdsaf
 *
 * @author kiwipeach
 * @create 2019-07-29
 */

import org.junit.Test;

import java.util.*;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class Main {
    public static ListNode ReverseList(ListNode head) {
        //1.特殊情况
        if (head == null) return null;
        //2.借助辅助栈来反转链表
        Stack<ListNode> stack = new Stack<ListNode>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        //3.弹出栈中的元素，即为反向
        //新的反向链表的头指针
        ListNode resultHead = stack.peek();
        ListNode newHead = stack.peek();
        while (true) {
            ListNode pop = stack.pop();
            newHead.next = pop;
            newHead = pop;
            if (stack.isEmpty()) {
                //给最后一个节点加上结尾null
                newHead.next = null;
                break;
            }
        }
        return resultHead;
    }

    public ListNode Merge(ListNode list1, ListNode list2) {
        //1.特殊情况
        if (list1 == null && list2 == null) return null;
        if (list1 == null && list2 != null) return list2;
        if (list1 != null && list2 == null) return list1;

        //2.指针p1和指针p2比较大小，谁更小就取出来进行链表构建，取出后，移动对应指针
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode p = null;
        ListNode insertHead = new ListNode(0);
        ListNode resultHead = null;
        boolean isFirstTime = true;
        while (true) {
            if (isFirstTime) {
                resultHead = p;
                isFirstTime = false;
            }
            if (p1.val < p2.val) {
                p = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                p = new ListNode(p2.val);
                p2 = p2.next;
            }

            p.next = insertHead;
            insertHead.next = p;
            //遍历结束了
            if (p1 == null) {
                insertHead.next = p2;
                break;
            }
            if (p2 == null) {
                insertHead.next = p1;
                break;
            }

        }
        return resultHead;
    }

    @Test
    public void 链表反向() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        ListNode head = node1;
        ListNode listNode = ReverseList(head);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    @Test
    public void 有序链表合并() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        node4.next = node5;
        node5.next = node6;

        ListNode listNode = Merge(node1,node2);
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }
}