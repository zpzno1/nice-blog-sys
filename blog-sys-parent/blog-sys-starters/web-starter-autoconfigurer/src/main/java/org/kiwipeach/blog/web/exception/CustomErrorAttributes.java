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
package org.kiwipeach.blog.web.exception;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常输出，能够自适应客户端进行视图数据和报文数据返回
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/27
 */
@Configuration
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(requestAttributes, includeStackTrace);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code",errorAttributes.get("status"));
        resultMap.put("msg",errorAttributes.get("error"));
        resultMap.put("path",errorAttributes.get("path"));
        resultMap.put("time",new Date());
        resultMap.put("data",null);
        //获取请求域中的参
        //errorAttributes.put("paramMap",requestAttributes.getAttribute("paramMap",RequestAttributes.SCOPE_REQUEST));
        return resultMap;
    }
}
