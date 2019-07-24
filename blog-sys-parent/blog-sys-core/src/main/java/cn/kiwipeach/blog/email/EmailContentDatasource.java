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
package cn.kiwipeach.blog.email;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 描述：邮件内容类型实体
 *
 * @author kiwipeach
 * @create 2019-07-24
 */
@Getter
@Setter
public class EmailContentDatasource {

    public static final String CONTENT_KEY = "content";

    private Map<String, Object> emailContent = new HashMap<>();

    public EmailContentDatasource() {
    }

    public EmailContentDatasource(String content) {
        this.emailContent.put(CONTENT_KEY, content);
    }

    public EmailContentDatasource(Set<String> paramSet) {
        this.emailContent.put(CONTENT_KEY, paramSet);
    }

    public Set<String> getParamSet() {
        return (Set<String>) getEmailContent().get(CONTENT_KEY);
    }

    //public static void main(String[] args) {
    //    Set<String> paramSet = new HashSet<>();
    //    paramSet.add("在乎你的在乎");
    //    paramSet.add("收益匪浅啊，兄弟！");
    //    paramSet.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    //    EmailContentDatasource datasource = new EmailContentDatasource(paramSet);
    //    Set<String> paramSet1 = datasource.getParamSet();
    //    System.out.println(paramSet1);
    //}

}
