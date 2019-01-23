/*
 * Copyright 2019 kiwipeach(1099501218@qq.com).
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
package cn.kiwipeach.blog.oauth.qq;

/**
 * qq登陆配置
 * @author kiwipeach
 * @create 2019-01-20
 */
public class Client {
    private String app_ID;
    private String app_KEY;
    private String accessTokenURL;
    private String authorizeURL;
    private String redirectURI;
    private String getOpenIDURL;
    private String scope;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getApp_ID() {
        return app_ID;
    }

    public void setApp_ID(String app_ID) {
        this.app_ID = app_ID;
    }

    public String getApp_KEY() {
        return app_KEY;
    }

    public void setApp_KEY(String app_KEY) {
        this.app_KEY = app_KEY;
    }

    public String getAccessTokenURL() {
        return accessTokenURL;
    }

    public void setAccessTokenURL(String accessTokenURL) {
        this.accessTokenURL = accessTokenURL;
    }

    public String getAuthorizeURL() {
        return authorizeURL;
    }

    public void setAuthorizeURL(String authorizeURL) {
        this.authorizeURL = authorizeURL;
    }

    public String getRedirectURI() {
        return redirectURI;
    }

    public void setRedirectURI(String redirectURI) {
        this.redirectURI = redirectURI;
    }

    public String getGetOpenIDURL() {
        return getOpenIDURL;
    }

    public void setGetOpenIDURL(String getOpenIDURL) {
        this.getOpenIDURL = getOpenIDURL;
    }
}
