package cn.kiwipeach.junit.http;/*
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

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * apache案例地址:http://hc.apache.org/httpcomponents-client-ga/httpclient/examples/org/apache/http/examples/client/
 * 中文博客参考：http://huangqiqing123.iteye.com/blog/2344680（各种get/post/delete/put请求案例，封装案例）
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/09/08
 */
public class Apache的HttpClient工具类的使用 {
    private Logger logger = LoggerFactory.getLogger(Apache的HttpClient工具类的使用.class);

    private CloseableHttpClient httpclient;
    private HttpEntity entity;
    private CloseableHttpResponse response;

    @Before
    public void init() {
        //可能会被淘汰，但是还未被淘汰目前
        //httpclient = HttpClients.createDefault();
        httpclient = HttpClientBuilder.create().build();
    }

    @After
    public void destroy() throws IOException {
        if (response != null) {
            entity = response.getEntity();
            //1)打印方案一, EntityUtils.toString会自动释放资源，但是官方不推荐
            logger.info("响应内容:{}", EntityUtils.toString(entity));
            //2)打印方案二，IOUtils.toString不会释放资源，需手工释放，官方推荐
            InputStream content = response.getEntity().getContent();
            //logger.info("响应内容:{}", IOUtils.toString(content, "UTF-8"));
            EntityUtils.consume(entity);//content.close();
            response.close();
        }
        //关闭entity,response,httpclient资源
        if (httpclient != null) {
            httpclient.close();
        }
    }

    /**
     * 测试1：https://postman-echo.com/get?test=123
     */
    @Test
    public void GET请求测试() throws IOException, URISyntaxException {
        //1)传统方式
        //HttpGet httpGet = new HttpGet("https://postman-echo.com/get?name=kiwipeach");
        //response = httpclient.execute(httpGet);
        //2）高级方式
        URI uri = new URIBuilder("https://postman-echo.com/get")
                .addParameter("name", "kiwipeach")
                .addParameter("email", "kiwipeach@qq.com").build();
        HttpGet httpGet = new HttpGet(uri);
        response = httpclient.execute(httpGet);
    }

    /**
     * 测试2：POST提交简单文本
     */
    @Test
    public void POST请求测试_纯文本() throws IOException {
        HttpPost httpPost = new HttpPost("https://postman-echo.com/post?name=kiwipeach");
        httpPost.setHeader(new BasicHeader("Content-Type", "text/plain"));
        entity = new StringEntity("kiwipeach say hello to apache.", ContentType.TEXT_PLAIN);
        httpPost.setEntity(entity);
        response = httpclient.execute(httpPost);
    }

    /**
     * 测试3：POST提交表单数据
     */
    @Test
    public void POST请求测试_表单() throws IOException {
        HttpPost httpPost = new HttpPost("https://postman-echo.com/post?name=kiwipeach");
        httpPost.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("username", "vip"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        response = httpclient.execute(httpPost);
    }


    /**
     * 测试3：POST提交表单数据
     */
    @Test
    public void POST请求测试_表单333() throws IOException {
        HttpPost httpPost = new HttpPost("https://gitee.com/oauth/token");
        //httpPost.setHeader(new BasicHeader("user-agent", "PostmanRuntime/6.4.1"));
        httpPost.setHeader(new BasicHeader("user-agent", "*"));
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nvps.add(new BasicNameValuePair("code", "4393470894b1f842c2960e8e191672bfda4f0622602d2acdd775c44c9a7c857e"));
        nvps.add(new BasicNameValuePair("client_id", "5dd66a99095b62d59758399d1c220ec746b683b54bfbe87dcfd9fb4618be9e17"));
        nvps.add(new BasicNameValuePair("client_secret", "c6eb84d1ab96d3697d489cc615b26629c8ced21aef14fb0d7ee27db88a0bc264"));
        nvps.add(new BasicNameValuePair("redirect_uri", "http://www.kiwipeach.cn/gitee/oauth2.0/callback"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        response = httpclient.execute(httpPost);
    }

    /**
     * 测试4：POST提交表单数据
     */
    @Test
    public void POST请求测试_二进制文件加表单() throws IOException {
        HttpPost httpPost = new HttpPost("https://postman-echo.com/post?name=kiwipeach");
        //使用httpmime构造multipart/form-data请求类型
        InputStream resourceAsStream = this.getClass().getResourceAsStream("student.json");
        ContentBody name = new StringBody("kiwipeach", ContentType.TEXT_PLAIN);
        ContentBody pic_body = new FileBody(new File("src/test", "qq_callback.png"), ContentType.APPLICATION_OCTET_STREAM, "qq_callback.ping");
        HttpEntity httpEntity = MultipartEntityBuilder.create()
                // 1)相当于<input type="text" name="userName" value=userName>
                .addTextBody("content", "kiwipeah say hello to apache.", ContentType.TEXT_PLAIN)
                .addPart("name", name)
                // 2)相当于<input type="file" name="file"/>
                .addBinaryBody("json_file", resourceAsStream, ContentType.APPLICATION_OCTET_STREAM, "student.json")
                .addPart("pic_file", pic_body)
                .build();
        httpPost.setEntity(httpEntity);
        response = httpclient.execute(httpPost);
    }

    /**
     * 测试5：POST请求测试_单个文件
     */
    @Test
    public void POST请求测试_单个文件() throws IOException {
        HttpPost httpPost = new HttpPost("https://postman-echo.com/post?name=kiwipeach");
        httpPost.setHeader(new BasicHeader("content-type", ContentType.TEXT_PLAIN.toString()));
        //1)文本类型
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //2)文件类型
        InputStream resourceAsStream = this.getClass().getResourceAsStream("qq_callback.png");
        builder.addBinaryBody("picture", resourceAsStream, ContentType.APPLICATION_OCTET_STREAM, "qq_callback.png");
        httpPost.setEntity(builder.build());
        response = httpclient.execute(httpPost);
    }


    /**
     * 测试6：PUT请求
     */
    @Test
    public void PUT请求() throws IOException {
        HttpPut httpPut = new HttpPut("https://postman-echo.com/put");
        httpPut.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        HttpEntity entity = new StringEntity("{\"id\":\"1007\",\"email\":\"1099501218@qq.com\"}", ContentType.APPLICATION_JSON);
        httpPut.setEntity(entity);
        response = httpclient.execute(httpPut);
    }

    ///**
    // * 测7试：DELETE请求
    // * https://stackoverflow.com/questions/3773338/httpdelete-with-body（Delete method是通过url传递参数的，如果使用body传递参数呢？）
    // */
    //@Test
    //public void DELETE请求() throws IOException {
    //    //HttpDelete httpDelete = new HttpDelete("https://postman-echo.com/delete");
    //    HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("https://postman-echo.com/delete");
    //    httpDelete.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
    //    HttpEntity entity = new StringEntity("{\"id\":\"1007\"}", ContentType.APPLICATION_JSON);
    //    httpDelete.setEntity(entity);
    //    response = httpclient.execute(httpDelete);
    //}
    //
    //@Test
    //public void 测试读取JSON文件() throws IOException {
    //    InputStream resourceAsStream = this.getClass().getResourceAsStream("student.json");
    //    System.out.println(IOUtils.toString(resourceAsStream, "UTF-8"));
    //    resourceAsStream.close();
    //}

}
