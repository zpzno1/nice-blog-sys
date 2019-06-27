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

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：xss漏洞处理
 *
 * @author kiwipeach
 * @create 2019-06-23
 */
@Slf4j
public class XssSqlFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        //String curReqestUrl = ((RequestFacade) request).getRequestURI();
        String contextPath = request.getServletContext().getContextPath();
        if (isNeedtoDoXss(contextPath)) {
            log.trace("执行xss过滤:{}", contextPath);
            chain.doFilter(new BlogHttpServletRequestWrapper(
                    (HttpServletRequest) request), response);
        } else {
            log.trace("忽略xss过滤：{}", contextPath);
            chain.doFilter(request, response);
        }
    }


    /**
     * 是否为排除过滤的地址
     *
     * @param curReqestUrl 当前地址
     * @return 返回是否包含在当前地址栏信息中
     */
    public boolean isNeedtoDoXss(String curReqestUrl) {
        String[] excludeUrlPatterns = filterConfig.getInitParameter("excludeUrlPatterns").split(",");
        boolean isNeedtoDoXss = true;
        for (String excludeRegex : excludeUrlPatterns) {
            Pattern pattern = Pattern.compile(excludeRegex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(curReqestUrl);
            //如果在排除的url中找到该地址，则表示为需要排除过滤资源(静态资源文件等..)
            if (matcher.find()) {
                isNeedtoDoXss = false;
                break;
            } else {
                continue;
            }
        }
        return isNeedtoDoXss;
    }

}
