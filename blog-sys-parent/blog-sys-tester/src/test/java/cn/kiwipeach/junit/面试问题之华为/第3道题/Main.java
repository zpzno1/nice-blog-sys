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
package cn.kiwipeach.junit.面试问题之华为.第3道题;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-07-28
 */
public class Main {
    public static void main(String args[]) throws IOException {
        //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String passwordInput = "";
        String decryptPasswordInput = "";
        String userInput = "";
        while ((userInput = br.readLine()) != null) {
            //加密
            passwordInput = userInput;
            userInput = br.readLine();
            decryptPasswordInput = userInput;
            String encrypt = encrypt(passwordInput);
            System.out.println(encrypt);

            //解密
            String decryptPassword = unEncrypt(decryptPasswordInput);
            System.out.println(decryptPassword);
        }
    }

    //加密
    public static String encrypt(String password) {
        StringBuilder sb = new StringBuilder();
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (isNumber(ch)) {
                sb.append(getEncryptNum(ch, true));
            } else if (isChar(ch)) {
                sb.append(nextEncrypyChar(ch, true));
            } else {
                throw new RuntimeException("参数异常");
            }
        }
        return sb.toString();
    }

    //解密
    public static String unEncrypt(String encrpyPassword) {
        StringBuilder sb = new StringBuilder();
        char[] chars = encrpyPassword.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (isNumber(ch)) {
                sb.append(getEncryptNum(ch, false));
            } else if (isChar(ch)) {
                sb.append(nextEncrypyChar(ch, false));
            } else {
                throw new RuntimeException("参数异常");
            }
        }
        return sb.toString();
    }

    private static char getEncryptNum(char ch, boolean isEncrypt) {
        if (isEncrypt) {
            return '9' == ch ? '0' : (char) (ch + 1);
        } else {
            return '0' == ch ? '9' : (char) (ch - 1);
        }
    }

    private static char nextEncrypyChar(char ch, boolean isEncrypt) {
        //加密
        if (isEncrypt) {
            //小写
            if (isLowerCase(ch)) {
                return 'z' == ch ? 'A' : (char) (ch - 32 + 1);
            } else {
                //大写
                return 'Z' == ch ? 'a' : (char) (ch + 32 + 1);
            }
            //解密
        } else {
            //小写
            if (isLowerCase(ch)) {
                return 'a' == ch ? 'Z' : (char) (ch - 32 - 1);
            } else {
                //大写
                return 'A' == ch ? 'z' : (char) (ch + 32 - 1);
            }
        }
    }

    private static boolean isNumber(char ch) {
        return ch >= '0' && ch <= '9';
    }

    private static boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private static boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private static boolean isChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }
}
