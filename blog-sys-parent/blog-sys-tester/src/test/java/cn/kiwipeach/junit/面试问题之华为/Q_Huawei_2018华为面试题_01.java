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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：华为2018面试器
 *
 * @author kiwipeach
 * @create 2019-07-26
 */
public class Q_Huawei_2018华为面试题_01 {
    /**
     * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，她最多可以换多少瓶汽水喝？”答案是5瓶，
     * 方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借
     * 给你一瓶汽水，喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，最多可以换多少瓶汽水喝？
     * <p>
     * 输入描述:
     * 输入文件最多包含10组测试数据，每个数据占一行，仅包含一个正整数n（1<=n<=100），表示小张手上的空汽水瓶数。n=0表示输入结束，你的程序不应当处理这一行。
     * 输出描述:
     * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
     * 输入例子1:
     * 3
     * 10
     * 81
     * 输出例子1:
     * 1
     * 5
     * 40
     */
    public static void main(String[] args) throws IOException {
        //空瓶换水问题
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        List<String> inputStrList = new ArrayList<>();
        while ((userInput = br.readLine()) != null) {
            inputStrList.add(userInput);
        }
        for (String input : inputStrList) {
            System.out.println(drinkWater(Integer.parseInt(input)));
        }
    }

    public static int drinkWater(int n) {
        if (n > 0 && n < 4) {
            return 1;
        } else {
            int nTemp = n / 3;
            int newN = nTemp + n % 3;
            return nTemp + drinkWater(newN);
        }
    }
}
