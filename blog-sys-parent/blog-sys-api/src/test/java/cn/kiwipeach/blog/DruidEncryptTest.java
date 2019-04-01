/*
 * Copyright 2018 kiwipeach.
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
package cn.kiwipeach.blog;

import com.alibaba.druid.filter.config.ConfigTools;
import org.junit.Test;

/**
 * druid数据源加密rsa算法
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/28
 */
public class DruidEncryptTest {
    /**
     * 获取数据源密码和公钥
     */
    @Test
    public void encryptPassword() throws Exception {
        String password = "lbr5078930198";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
    }

    @Test
    public void decryptPassword() throws Exception {
        //1)测试密文数据准备
        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAwMcSo91GJ8n1jRyLXLcu3qrVdVfAsmVhicOdPg7q9Mjp+m+wLSayMRRW0dpRQMSAWC/R6Q9K4KrrzOrj6vwtiQIDAQABAkB77n8ZQWOcqMVnlnYxgMJs+76G1YdYKLzv6AXqo4SBIJicOR1gryWY88BzUxBQJ0D2Fe8k4IPW4+fZQVd1rbuRAiEA/rKue+UqbeVuVl8gsii0MatzgarLkhiTfDMu7Xz5gFcCIQDBw1t0oPYUXcV98DAubDuo7wuQK+RxjGzmAVyWT6WVHwIhALw+6ENeD0wGRNTFnm7PMs97PvXZfHhQfcFQOyEAO5AlAiA7Iwasp9uzB5OZBk+OO5ZRzvN2gw7r4PHzmQLLFW/x/QIgdWJJJ5q1KbdBmPTs1FZHBSRRd5NyzN+jGgTBYh5TmV0=";
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAMDHEqPdRifJ9Y0ci1y3Lt6q1XVXwLJlYYnDnT4O6vTI6fpvsC0msjEUVtHaUUDEgFgv0ekPSuCq68zq4+r8LYkCAwEAAQ==";
        //String cipherText = "Cnzg8hutIv6NPHhylRPqjvC7OkQOqdb543vwfW5JCfsBnBDtMctnENGO3MfMF3VJo8Q/Ixou8Q/6d2hipLBEew==";
        String password = "kiwipeach";

        //2)钥获+密码=密码密文
        String encryptPassword = ConfigTools.encrypt(privateKey,password);
        System.out.println("加密密文:"+encryptPassword);

        //3)公钥+密码=获取密码
        String decryptPassword = ConfigTools.decrypt(publicKey,encryptPassword);//解密成密码明文
        System.out.println("原始密码:"+decryptPassword);
    }
}
