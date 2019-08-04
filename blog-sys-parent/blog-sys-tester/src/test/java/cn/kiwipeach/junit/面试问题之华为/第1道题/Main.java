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
package cn.kiwipeach.junit.面试问题之华为.第1道题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    static class ArrayItem {
        public boolean isChar;
        public char ch;

        public ArrayItem(boolean isChar) {
            this.isChar = isChar;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String useInput;
        while ((useInput = br.readLine()) != null) {
            TreeSet<ArrayItem> sequenceSet = new TreeSet<ArrayItem>(new Comparator<ArrayItem>() {
                @Override
                public int compare(ArrayItem o1, ArrayItem o2) {
                    if ((o1.ch >= 'a' && o1.ch <= 'z') && (o2.ch >= 'A' && o2.ch <= 'Z')) {
                        return o1.ch >= o2.ch + 32 ? 1 : -1;
                    } else if ((o1.ch >= 'A' && o1.ch <= 'Z') && (o2.ch >= 'a' && o2.ch <= 'z')) {
                        return o1.ch + 32 >= o2.ch ? 1 : -1;
                    } else {
                        return o1.ch - o2.ch >= 0 ? 1 : -1;
                    }
                }
            });
            int len = useInput.length();
            ArrayItem arrayItems[] = new ArrayItem[len];
            for (int i = 0; i < len; i++) {
                arrayItems[i] = new ArrayItem(true);
            }
            for (int i = 0; i < len; i++) {
                char curCh = useInput.charAt(i);
                if (!isChar(curCh)) {
                    arrayItems[i].ch = curCh;
                    //收集非字母
                    arrayItems[i].isChar = false;
                } else {
                    //收集字母并存放到排序集合中
                    ArrayItem arrayItem = new ArrayItem(true);
                    arrayItem.ch = curCh;
                    sequenceSet.add(arrayItem);
                }
            }
            StringBuilder sb = new StringBuilder();
            for (ArrayItem item : arrayItems) {
                if (item.isChar) {
                    sb.append(sequenceSet.pollFirst().ch);
                } else {
                    sb.append(item.ch);
                }
            }
            System.out.println(sb.toString());
        }
    }

    private static boolean isChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}