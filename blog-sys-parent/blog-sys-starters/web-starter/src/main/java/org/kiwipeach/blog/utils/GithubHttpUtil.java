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
package org.kiwipeach.blog.utils;

import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.oauth.github.ConfigProperties;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URIBuilder;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * github http请求工具类
 *
 * @author kiwipeach
 * @create 2019-01-28
 */
public class GithubHttpUtil extends HttpBaseUtil {

    /**
     * 获取github的登陆条状url
     *
     * @param githubConfig github三方授权登陆配置信息
     * @return 返回统一认证授权地址
     */
    public static String getLoginUrl(ConfigProperties githubConfig) throws URISyntaxException {
        URI uri = new URIBuilder(githubConfig.getClient().getUserAuthorizationUri())
                .addParameter("client_id", githubConfig.getClient().getClientId())
                .addParameter("redirect_uri", githubConfig.getClient().getRedirectURI())
                .addParameter("state", "__kiwipeach__").build();
        return uri.toString();
    }


    /**
     * 通过授权码获取token字符串
     *
     * @param githubConfig github配置实体类
     * @param code         授权码
     * @return 返回token字符串
     * @throws URISyntaxException 地址异常
     * @throws IOException        网络异常
     */
    public static String getAccessTokenString(ConfigProperties githubConfig, String code, String returnKey) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(githubConfig.getClient().getAccessTokenUri())
                .addParameter("grant_type", "authorization_code")
                .addParameter("client_id", githubConfig.getClient().getClientId())
                .addParameter("client_secret", githubConfig.getClient().getClientSecret())
                .addParameter("code", code)
                .addParameter("redirect_uri", githubConfig.getClient().getRedirectURI()).build();
        String resultStr = getResponse(uri);
        String tokenStr = null;
        if (resultStr.contains("access_token")) {
            List<NameValuePair> queryParams = new URIBuilder("?" + resultStr).getQueryParams();
            for (NameValuePair nameValuePair : queryParams) {
                String name = nameValuePair.getName();
                if (name.equalsIgnoreCase(returnKey)) {
                    tokenStr = nameValuePair.getValue();
                    break;
                }
            }
        } else {
            throw new BlogException("-ACCOUNT_002", "github授权码登陆失败,错误信息:" + resultStr);
        }
        return tokenStr;
    }


    public static AccessToken getAccessToken(ConfigProperties githubConfig, String accessTokenStr) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(githubConfig.getResource().getUserInfoUri())
                .addParameter("access_token", accessTokenStr).build();
        String resultStr = getResponse(uri);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        AccessToken accessToken = null;
        // 获取信息成功
        if (!StringUtils.isEmpty(jsonObject.getString("login"))) {
            String loginId = jsonObject.getString("node_id");
            String userName = jsonObject.getString("login");
            String nickName = jsonObject.getString("login");
            String headUrl = jsonObject.getString("avatar_url");
            accessToken = new AccessToken(accessTokenStr, loginId,userName, nickName, headUrl, "github");
            // 获取信息失败
        } else {
            throw new BlogException("-ACCOUNT_002", "github查询个人信息异常,错误信息:" + resultStr);
        }
        return accessToken;
    }


}
