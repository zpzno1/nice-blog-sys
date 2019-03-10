/*
* Copyright 2019 kiwipeach[1099501218@qq.com].
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
package cn.kiwipeach.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * 系统访问日志
 *
 * @author kiwipeach
 * @create 2019-03-10
 */
@TableName("SYS_ACCESSLOG")
public class SysAccesslog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId("ID")
    private String id;

    /**
     * 用户编号
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * ip
     */
    @TableField("IP")
    private String ip;

    /**
     * 访问开始时间
     */
    @TableField("START_TIME")
    private LocalDateTime startTime;

    /**
     * 访问地址
     */
    @TableField("URL")
    private String url;

    /**
     * 请求方法
     */
    @TableField("METHOD")
    private String method;

    /**
     * 请求头信息
     */
    @TableField("REQUEST_HEADER")
    private String requestHeader;

    /**
     * 入参
     */
    @TableField("REQUEST_BODY")
    private String requestBody;

    /**
     * 返回信息
     */
    @TableField("RESPONSE_BODY")
    private String responseBody;

    /**
     * http相应状态码
     */
    @TableField("STATUS")
    private String status;


    /**
     * 访问结束时间
     */
    @TableField("END_TIME")
    private LocalDateTime endTime;

    /**
     * 统计目标功能名称
     */
    @TableField("NAME")
    private String name;

    public SysAccesslog() {
        this.startTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "SysAccesslog{" +
        "id=" + id +
        ", userName=" + userName +
        ", ip=" + ip +
        ", startTime=" + startTime +
        ", url=" + url +
        ", method=" + method +
        ", requestHeader=" + requestHeader +
        ", requestBody=" + requestBody +
        ", responseBody=" + responseBody +
        ", status=" + status +
        ", endTime=" + endTime +
        "}";
    }
}
