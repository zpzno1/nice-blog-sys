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
package org.kiwipeach.blog.filter.xss;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import static org.kiwipeach.blog.filter.xss.XssHelper.HTMLEncode;

/**
 * 描述：重写HttpServletRequestWrapper类
 *
 * @author kiwipeach
 * @create 2019-06-23
 */
public class BlogHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest orgRequest = null;


    public BlogHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(XssHelper.xssEncode(name));
        if (value != null) {
            value = XssHelper.xssEncode(value);
            value = XssHelper.HTMLEncode(value);
            value = SqlHelper.sqlEncode(value);
        }
        return value;
    }

    /**
     * spring是使用的getParameterValues方法
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        if (value == null) {
            return null;
        }
        for (int i = 0; i < value.length; i++) {
            value[i] = XssHelper.xssEncode(value[i]);
            value[i] = HTMLEncode(value[i]);
            value[i] = SqlHelper.sqlEncode(value[i]);
            //TODO sql过滤或者cros攻击等..
        }

        return value;
    }


    /**
     * 覆盖getHeader方法，将参数名和参数值都做xss过滤。 如果需要获得原始的值，则通过super.getHeaders(name)来获取
     * getHeaderNames 也可能需要覆盖
     */
    @Override
    public String getHeader(String name) {

        String value = super.getHeader(XssHelper.xssEncode(name));
        if (value != null) {
            value = XssHelper.xssEncode(value);
        }
        return value;
    }

    /**
     * 获取最原始的request
     *
     * @return
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request的静态方法
     *
     * @return
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest req) {
        if (req instanceof BlogHttpServletRequestWrapper) {
            return ((BlogHttpServletRequestWrapper) req).getOrgRequest();
        }

        return req;
    }


}
