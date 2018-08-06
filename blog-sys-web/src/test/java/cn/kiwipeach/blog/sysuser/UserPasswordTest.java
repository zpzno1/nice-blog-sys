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
package cn.kiwipeach.blog.sysuser;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * 用户密码加密规则
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/05
 */
public class UserPasswordTest {

    /**
     * 采用sha1盐值加密1024次得到的加密结果，其密码可认为是不可逆的了。
     */
    @Test
    public void encryptUserPwd(){
        String hashAlgorithmName = "SHA1";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("kiwipeach");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);
        //测试中:3a322406a9067b292325e13989c404dd1bf38ebf
        //程序中:3a322406a9067b292325e13989c404dd1bf38ebf
    }

}
