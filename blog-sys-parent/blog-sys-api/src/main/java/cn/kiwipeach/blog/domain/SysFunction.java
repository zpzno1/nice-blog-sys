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
package cn.kiwipeach.blog.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.sql.Clob;
import java.time.LocalDateTime;

/**
 * 系统菜单表
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("SYS_FUNCTION")
public class SysFunction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    @TableId("ID")
    private String id;

    /**
     * 功能路径(页面或操作)
     */
    @TableField("LOCATION")
    private String location;

    /**
     * 功能名称
     */
    @TableField("TEXT")
    private String text;

    /**
     * 父节点编号
     */
    @TableField("PARENTID")
    private String parentid;

    /**
     * 同级权重
     */
    @TableField("WEIGHT")
    private Integer weight;

    /**
     * 节点类型(页面或操作)
     */
    @TableField("NODE_TYPE")
    private String nodeType;

    /**
     * 图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 描述信息
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 菜单权限标志编号
     */
    @TableField("PERMISSION_ID")
    private String permissionId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "SysFunction{" +
        "id=" + id +
        ", location=" + location +
        ", text=" + text +
        ", parentid=" + parentid +
        ", weight=" + weight +
        ", nodeType=" + nodeType +
        ", icon=" + icon +
        ", description=" + description +
        ", permissionId=" + permissionId +
        "}";
    }

    /**
     * 系统访问日志
     *
     * @author kiwipeach
     * @create 2019-03-10
     */
    @TableName("SYS_ACCESSLOG")
    public static class SysAccesslog implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 编号
         */
        @TableId("ID")
        private String id;

        /**
         * 用户编号
         */
        @TableField("USERID")
        private String userid;

        /**
         * ip
         */
        @TableField("IP")
        private String ip;

        /**
         * 访问时间
         */
        @TableField("TIME")
        private LocalDateTime time;

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
         * 通常信息
         */
        @TableField("GENERAL")
        private String general;

        /**
         * 请求头信息
         */
        @TableField("REQUEST_HEADER")
        private String requestHeader;

        /**
         * 入参
         */
        @TableField("REQUEST_BODY")
        private Clob requestBody;

        /**
         * 响应体信息
         */
        @TableField("RESPONSE_HEADER")
        private String responseHeader;

        /**
         * 返回信息
         */
        @TableField("RESPONSE_BODY")
        private Clob responseBody;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
        public LocalDateTime getTime() {
            return time;
        }

        public void setTime(LocalDateTime time) {
            this.time = time;
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
        public String getGeneral() {
            return general;
        }

        public void setGeneral(String general) {
            this.general = general;
        }
        public String getRequestHeader() {
            return requestHeader;
        }

        public void setRequestHeader(String requestHeader) {
            this.requestHeader = requestHeader;
        }
        public Clob getRequestBody() {
            return requestBody;
        }

        public void setRequestBody(Clob requestBody) {
            this.requestBody = requestBody;
        }
        public String getResponseHeader() {
            return responseHeader;
        }

        public void setResponseHeader(String responseHeader) {
            this.responseHeader = responseHeader;
        }
        public Clob getResponseBody() {
            return responseBody;
        }

        public void setResponseBody(Clob responseBody) {
            this.responseBody = responseBody;
        }

        @Override
        public String toString() {
            return "SysAccesslog{" +
            "id=" + id +
            ", userid=" + userid +
            ", ip=" + ip +
            ", time=" + time +
            ", url=" + url +
            ", method=" + method +
            ", general=" + general +
            ", requestHeader=" + requestHeader +
            ", requestBody=" + requestBody +
            ", responseHeader=" + responseHeader +
            ", responseBody=" + responseBody +
            "}";
        }
    }
}
