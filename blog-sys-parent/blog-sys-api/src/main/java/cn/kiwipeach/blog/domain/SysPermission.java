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
import java.time.LocalDateTime;

/**
 * 系统权限
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("SYS_PERMISSION")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    @TableId("ID")
    private String id;

    /**
     * 权限名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 权限权重
     */
    @TableField("WEIGHT")
    private Integer weight;

    /**
     * 权限图标
     */
    @TableField("ICON")
    private String icon;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 权限英文编码(格式:资源+模块+操作,blog:page:access)
     */
    @TableField("CODE")
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
        "id=" + id +
        ", name=" + name +
        ", weight=" + weight +
        ", icon=" + icon +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        ", code=" + code +
        "}";
    }
}
