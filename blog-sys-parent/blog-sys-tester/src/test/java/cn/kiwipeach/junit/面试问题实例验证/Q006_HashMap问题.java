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

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：
 *
 * @author kiwipeach
 * @create 2019-08-12
 */
public class Q006_HashMap问题 {

    @Test
    public void 测试map的key能否为null() {
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        System.out.println(map);
        System.out.println(-3 >>> 1 );
    }

    @Test
    public void 强制转换() {


        // 包装类型
        Long aLong = 10086L;
        Integer cInteger = 10;
        //Integer bInteger = Math.toIntExact(aLong);
        //Integer bInteger = aLong;
        Long c = Long.valueOf(cInteger);

        // 基础类型
        int aInt = 10;
        long bLong = 10;
        int cInt = (int) bLong;
    }
    class Person implements Cloneable{
        public Integer id;
        public String name;

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
    @Test
    public void 浅拷贝() throws CloneNotSupportedException {
        Person p = new Person();
        p.name = "kiwipeach";
        p.id = 1;
        Person copyP = (Person) p.clone();
        copyP.name = "kakaluote444";
        copyP.id=100;

        System.out.println(p.name);
        System.out.println(p.id);

    }


}
