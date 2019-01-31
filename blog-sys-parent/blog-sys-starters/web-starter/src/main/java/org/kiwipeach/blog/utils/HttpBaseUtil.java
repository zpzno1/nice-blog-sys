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

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * http请求辅助类
 *
 * @author kiwipeach
 * @create 2019-01-30
 */
@Slf4j
public class HttpBaseUtil {

    /**
     * get公共方法
     *
     * @param uri 请求地址
     * @return 返回相应结果
     * @throws IOException
     */
    protected static String getResponse(URI uri) throws IOException {
        log.warn("GET:三方登陆请求:{}", uri.toString());
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
        String response = EntityUtils.toString(httpResponse.getEntity());
        log.warn("GET:三方登陆返回:{}", response);
        return response;
    }

    /**
     * post公共方法
     *
     * @param uri 请求地址
     * @return 返回相应结果
     * @throws IOException
     */
    protected static String postResponse(URI uri) throws IOException {
        log.warn("POST:三方登陆请求:{}", uri.toString());
        // 组装post请求参数
        URIBuilder uriBuilder = new URIBuilder(uri);
        // 设置头信息，并发送post请求
        String postUri = new StringBuffer(uriBuilder.getScheme()).append("://").append(uriBuilder.getHost()).append(uri.getPath()).toString();
        HttpPost httpPost = new HttpPost(postUri);
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        List<NameValuePair> nvps = new ArrayList<>();
        List<NameValuePair> queryParams = uriBuilder.getQueryParams();
        for (NameValuePair nameValuePair : queryParams) {
            nvps.add(new BasicNameValuePair(nameValuePair.getName(), nameValuePair.getValue()));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        CloseableHttpResponse httpResponse = httpclient.execute(httpPost);
        String response = EntityUtils.toString(httpResponse.getEntity());
        log.warn("POST:三方登陆返回:{}", response);
        return response;
    }


}
