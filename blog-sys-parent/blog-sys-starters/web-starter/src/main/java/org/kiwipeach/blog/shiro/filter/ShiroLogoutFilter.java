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
package org.kiwipeach.blog.shiro.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 描述：shiro自定义logout退出拦截器
 *
 * @author kiwipeach
 * @create 2019-07-04
 */
public class ShiroLogoutFilter extends LogoutFilter {

    private String logoutUrl;

    /**
     * 重写默认父类获取的跳转地址
     *
     * @return
     */
    @Override
    public String getRedirectUrl() {
        String defaultLogoutUrl = super.getRedirectUrl();
        return logoutUrl == null ? defaultLogoutUrl : logoutUrl;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        this.logoutUrl = request.getParameter("logoutUrl");
        return super.preHandle(request, response);
    }
}
