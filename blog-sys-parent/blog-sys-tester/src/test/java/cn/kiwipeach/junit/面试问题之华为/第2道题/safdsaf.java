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

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class safdsaf {

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = bf.readLine().toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (((charArray[i] >= 'a') && (charArray[i] <= 'z')) || (((charArray[i] >= 'A') && (charArray[i] <= 'Z')))) {
                continue;
            } else {
                charArray[i] = ' ';
            }
        }
        String ss = new String(charArray);
        String[] split = ss.trim().split(" ");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < split.length; i++) {
            if (!split[i].equals("")) {
                list.add(split[i]);
            }
        }
        swap(list);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
            sb.append(" ");
        }
        System.out.println(String.valueOf(sb.toString().trim()));
    }

    private static void swap(ArrayList<String> list) {
        int end = list.size() - 1;
        int start = 0;
        while (start < end) {
            String temp = list.get(end);
            list.set(end, list.get(start));
            list.set(start, temp);
            start++;
            end--;
        }
    }


    @Test
    public void test() {
        String text = "hellojava";
        String arr[] = new String[]{};
        System.out.println(arr.length);
        System.out.println(text.length());
        System.out.println(text.substring(0,5));
    }

}
