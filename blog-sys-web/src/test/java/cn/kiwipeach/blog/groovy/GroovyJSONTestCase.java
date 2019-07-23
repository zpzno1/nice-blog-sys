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
package cn.kiwipeach.blog.groovy;

import cn.kiwipeach.blog.domain.vo.UserInfoVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;
import lombok.extern.slf4j.Slf4j;
import org.apache.groovy.json.internal.LazyMap;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：groovy对json操作支持
 *
 * @author kiwipeach
 * @create 2019-07-18
 */
@Slf4j
public class GroovyJSONTestCase {

    public List<UserInfoVO> userList = new ArrayList<>();

    @Before
    public void init() {
        userList.add(new UserInfoVO("刘卜铷", "kiwipeach", "http://groovy-lang.org/json.html", "fdsafds", "wx"));
        userList.add(new UserInfoVO("黄莹", "huangying", "http://groovy-lang.org/json.html#_customizing_output", "jdtegafg", "qq"));
    }

    @Test
    public void 对象转字符串() {
        String userJsonString = JsonOutput.toJson(userList);
        log.info(userJsonString);
    }


    @Test
    public void 字符串转对象() {
        String userListString = "[{\"userName\":\"\\u5218\\u535c\\u94f7\",\"platform\":\"wx\",\"nickName\":\"kiwipeach\",\"headUrl\":\"http://groovy-lang.org/json.html\",\"openId\":\"fdsafds\"},{\"userName\":\"\\u9ec4\\u83b9\",\"platform\":\"qq\",\"nickName\":\"huangying\",\"headUrl\":\"http://groovy-lang.org/json.html#_customizing_output\",\"openId\":\"jdtegafg\"}]";
        //格式化
        String s = JsonOutput.prettyPrint(userListString);
        log.info("格式化json:{}",s);
        JsonSlurper jsonSlurper = new JsonSlurper();
        List<LazyMap> userInfoVOList = (List<LazyMap>) jsonSlurper.parseText(userListString);
        for (LazyMap item : userInfoVOList) {
            log.info("==>", item.toString());
        }
    }
}
