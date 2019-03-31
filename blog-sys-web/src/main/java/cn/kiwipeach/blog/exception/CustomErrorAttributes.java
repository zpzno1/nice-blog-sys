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
package cn.kiwipeach.blog.exception;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常输出，能够自适应客户端进行视图数据和报文数据返回
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/27
 */
@Component
@Slf4j
public class CustomErrorAttributes extends DefaultErrorAttributes {
    public CustomErrorAttributes() {
        log.info("自定义自适应异常类：{}", this.getClass());
    }


    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", errorAttributes.get("status"));
        resultMap.put("msg", errorAttributes.get("message"));
        JSONObject data = new JSONObject();
        data.put("path", errorAttributes.get("path"));
        //data.put("error", errorAttributes.get("error"));
        data.put("stack", webRequest.getAttribute("humanExceptionStack", RequestAttributes.SCOPE_REQUEST));
        resultMap.put("time", errorAttributes.get("timestamp"));
        resultMap.put("data", data);
        //获取请求域中的参
        //resultMap.put("paramMap", requestAttributes.getAttribute("humanExceptionStack", RequestAttributes.SCOPE_REQUEST));
        return resultMap;
    }

}
