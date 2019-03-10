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
package org.kiwipeach.blog.aop;

import cn.kiwipeach.blog.anno.AccessLog;
import cn.kiwipeach.blog.domain.SysAccesslog;
import cn.kiwipeach.blog.domain.vo.UserInfoVO;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.SysAccesslogMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.IPUtil;
import org.kiwipeach.blog.utils.UserUtil;
import org.kiwipeach.blog.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志访问切面
 *
 * @author kiwipeach
 * @create 2019-03-08
 */
@Aspect
@Component
public class SysAccessLogAspect {
    /**
     * 当前线程日志
     */
    private ThreadLocal<SysAccesslog> accesslogThreadLocal = new ThreadLocal<>();

    @Autowired
    private SysAccesslogMapper sysAccesslogMapper;

    /**
     * 这里我们使用注解的形式
     * 切点表达式: "execution(public * cn.kiwipeach.blog.controller.*.*(..))";
     */
    @Pointcut("@annotation(cn.kiwipeach.blog.anno.AccessLog)")
    public void logPointCut() {
    }

    @Before(value = "logPointCut()")
    public void before(JoinPoint joinPoint) {
        SysAccesslog sysAccesslog = new SysAccesslog();
        //功能接口名称
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AccessLog declaredAnnotation = signature.getMethod().getDeclaredAnnotation(AccessLog.class);
        sysAccesslog.setName(declaredAnnotation.value());
        //用户名
        AccessToken accessToken = UserUtil.getCurrentUser();
        String userName = accessToken == null ? "anno" : accessToken.getNickName();
        sysAccesslog.setUserName(userName);
        HttpServletRequest request = WebUtil.getRequest();
        //ip
        sysAccesslog.setIp(IPUtil.getIpAddr(request));
        //url
        StringBuffer url = new StringBuffer(request.getRequestURL().toString());
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            url.append("?").append(queryString);
        }
        sysAccesslog.setUrl(url.toString());
        //method
        sysAccesslog.setMethod(request.getMethod());
        //获取请求头信息
        sysAccesslog.setRequestHeader(craeteRequestHeaderInfo());
        //请求体信息
        sysAccesslog.setRequestBody(createRequestBodyInfo());
        accesslogThreadLocal.set(sysAccesslog);
    }

    @AfterReturning(value = "logPointCut()", returning = "result")
    public void afterRetruning(JoinPoint joinPoint, Object result) {
        logAndSaveResponse(joinPoint, JSON.toJSONString(result));
    }


    @AfterThrowing(value = "logPointCut()", throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception) {
        logAndSaveResponse(joinPoint, exception.getMessage());
    }


    /**
     * 构造通用请求题信息
     *
     * @return 返回请求JSON体
     */
    private String craeteRequestHeaderInfo() {
        HttpServletRequest request = WebUtil.getRequest();
        JSONObject requestResult = new JSONObject();
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headMap = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            String headerValue = request.getHeader(headName);
            headMap.put(headName, headerValue);
        }
        requestResult.put("headers", headMap);
        return JSON.toJSONString(headMap);
    }

    /**
     * 创建body信息
     * 要考虑GET请求时候：没有content-type信息
     * 非GET请求的时候：text/plain,application/json,application/javascript,application/xml,text/xml,text/html
     *
     * @return
     */
    private String createRequestBodyInfo() {
        HttpServletRequest request = WebUtil.getRequest();
        String header = request.getHeader("content-type");
        JSONObject resultObj = new JSONObject();
        if (!StringUtils.isEmpty(header)) {
            resultObj.put("requestBody", resolveRequestBody(request));
        }
        return JSON.toJSONString(resultObj);
    }


    /**
     * 获取请求体内容：text/plain,application/json,application/javascript,application/xml,text/xml,text/html
     *
     * @param request 请求对象
     * @return 返回请求体信息
     */
    private String resolveRequestBody(HttpServletRequest request) {
        // 包装成搞笑字符流
        StringBuffer bodyBuffer = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(request.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            bodyBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                bodyBuffer.append(line);
            }
        } catch (IOException e) {
            throw new BlogException("-ACCESSLOG_001", "获取请求body信息失败:" + e.getMessage());
        }
        return bodyBuffer.toString();
    }

    /**
     * 打印和记录当前日志信息
     *
     * @param joinPoint    切点
     * @param responseBody 相应信息
     */
    private void logAndSaveResponse(JoinPoint joinPoint, String responseBody) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class curClass = signature.getDeclaringType();
        Logger log = LoggerFactory.getLogger(curClass);
        SysAccesslog sysAccesslog = accesslogThreadLocal.get();
        HttpServletResponse response = WebUtil.getResponse();
        sysAccesslog.setStatus(String.valueOf(response.getStatus()));
        sysAccesslog.setEndTime(LocalDateTime.now());
        long durTime = Duration.between(sysAccesslog.getStartTime(), sysAccesslog.getEndTime()).toMillis();
        sysAccesslog.setResponseBody(responseBody);
        try {
            sysAccesslogMapper.insert(sysAccesslog);
        } catch (Exception e) {
            log.error("日志持久化异常:", e);
        }
        log.info("{}==>URL:{} {} ", signature.getMethod().getName(), sysAccesslog.getUrl(), sysAccesslog.getMethod());
        log.info("{}==>返回结果({}ms):{}", signature.getMethod().getName(), durTime, responseBody);
    }

}
