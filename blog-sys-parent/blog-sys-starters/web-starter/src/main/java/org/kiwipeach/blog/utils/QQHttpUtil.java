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

import cn.kiwipeach.blog.oauth.qq.ConfigProperties;
import cn.kiwipeach.blog.exception.BlogException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * qq http请求工具类
 *
 * @author kiwipeach
 * @create 2019-01-20
 */
public class QQHttpUtil extends HttpBaseUtil{


    /**
     * 获取QQ的登陆路径
     *
     * @param qqConfig qq配置类
     * @return 返回qq登陆地址
     */
    public static String getLoginUrl(ConfigProperties qqConfig) throws URISyntaxException {
        URI uri = new URIBuilder(qqConfig.getClient().getAuthorizeURL())
                .addParameter("response_type", "code")
                .addParameter("client_id", qqConfig.getClient().getApp_ID())
                .addParameter("redirect_uri", qqConfig.getClient().getRedirectURI())
                // FIXME qq官方建议我们把这个值，存在session中，在登陆的时候去验证该值
                .addParameter("state", "__kiwipeach__")
                .addParameter("scope", qqConfig.getClient().getScope())
                .addParameter("display", "pc").build();
        return uri.toString();
    }


    /**
     * 通过授权码获取AccessToken
     * 失败：callback( {"error":100019,"error_description":"code to access token error"} );
     * 成功：access_token=3632A2D1F7301B21DC7EB261EE9D1D3E&expires_in=7776000&refresh_token=501954626EB250E3242B7C5DC2ED3F36
     *
     * @param qqConfig qq属性配置
     * @param code     授权码
     * @return 返回AccessToken信息
     * @throws URISyntaxException uri异常
     * @throws IOException        异常
     */
    public static String getAccessTokenString(ConfigProperties qqConfig, String code, String type) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(qqConfig.getClient().getAccessTokenURL())
                .addParameter("grant_type", "authorization_code")
                .addParameter("client_id", qqConfig.getClient().getApp_ID())
                .addParameter("client_secret", qqConfig.getClient().getApp_KEY())
                .addParameter("code", code)
                .addParameter("redirect_uri", qqConfig.getClient().getRedirectURI()).build();
        String resultStr = getResponse(uri);
        String typeValue = null;
        if (resultStr.contains("access_token")) {
            List<NameValuePair> queryParams = new URIBuilder("?" + resultStr).getQueryParams();
            for (NameValuePair nameValuePair : queryParams) {
                if (nameValuePair.getName().equals(type)) {
                    typeValue = nameValuePair.getValue();
                    break;
                }
            }
        } else {
            throw new BlogException("-ACCOUNT_002", "qq授权码登陆失败,错误信息:" + resultStr);
        }
        if (StringUtils.isEmpty(typeValue)) {
            throw new BlogException("-ACCOUNT_002", "qq授权码登陆失败,错误参数:" + type);
        }
        return typeValue;
    }

    /**
     * 获取用户的openId
     * 成功:callback( {"client_id":"101495727","openid":"6FF96B97CF726B2D1DD31798135782FA"} );
     *
     * @param qqConfig
     * @param accessToken
     * @param returnKey
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String getOpenId(ConfigProperties qqConfig, String accessToken, String returnKey) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(qqConfig.getClient().getGetOpenIDURL())
                .addParameter("access_token", accessToken).build();
        String resultStr = getResponse(uri);
        String typeValue = null;
        if (resultStr.contains("openid")) {
            String result = resultStr.replace("callback(", "").replace(");", "");
            JSONObject jsonObject = JSONObject.parseObject(result);
            typeValue = jsonObject.getString(returnKey);
        } else {
            throw new BlogException("-ACCOUNT_002", "qq查询openId异常,错误信息:" + resultStr);
        }
        if (StringUtils.isEmpty(typeValue)) {
            throw new BlogException("-ACCOUNT_002", "qq查询openId异常,错误信息:" + resultStr);
        }
        return typeValue;
    }

    /**
     * 获取用户登录信息
     *
     * @param qqConfig
     * @param accessTokenString
     * @param openId
     * @return
     */
    public static AccessToken getAccessToken(ConfigProperties qqConfig, String accessTokenString, String openId) throws URISyntaxException, IOException {
        URI uri = new URIBuilder(qqConfig.getResource().getGetUserInfoURL())
                .addParameter("access_token", accessTokenString)
                .addParameter("oauth_consumer_key", qqConfig.getClient().getApp_ID())
                .addParameter("openid", openId).build();
        String resultStr = getResponse(uri);
        JSONObject jsonObject = JSONObject.parseObject(resultStr);
        AccessToken accessToken = null;
        if ("0".equals(jsonObject.getString("ret"))) {
            String nickName = jsonObject.getString("nickname");
            String headUrl = jsonObject.getString("figureurl");
            accessToken = new AccessToken(accessTokenString, openId, nickName, headUrl, "qq");
        } else {
            throw new BlogException("-ACCOUNT_002", "qq查询openId异常,错误信息:" + resultStr);
        }
        return accessToken;
    }




    public static void main(String[] args) {
        //获取jsonp中的参数方法
        String jsonp = "callback( {\"client_id\":\"101495727\",\"openid\":\"6FF96B97CF726B2D1DD31798135782FA\"} )";
//        Matcher m = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"").matcher(jsonp);
//        if (m.find()) {
//            System.out.println(m.group(1));
//        }
        String result = jsonp.replace("callback(", "").replace(")", "");
        JSONObject parse = JSONObject.parseObject(result);
        System.out.println(result);
    }


}
