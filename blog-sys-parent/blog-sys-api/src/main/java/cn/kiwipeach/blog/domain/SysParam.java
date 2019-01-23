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
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统参数
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("SYS_PARAM")
public class SysParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 参数编号
     */
    @TableField("ID")
    private String id;

    /**
     * 参数名称
     */
    @TableField("NAME")
    private String name;

    /**
     * 参数值
     */
    @TableField("VALUE")
    private Integer value;

    /**
     * 是否可用[0:不可用 1可用]
     */
    @TableField("ENABLE")
    private Integer enable;

    /**
     * 描述
     */
    @TableField("DESCRIPTION")
    private String description;

    /**
     * 父节点编号(具有级联属性的编码参数)
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * sys:系统参数单条 code:编码类型多条 cascade:级联类型多条
     */
    @TableField("TYPE")
    private String type;

    /**
     * 创建时间
     */
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

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
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "SysParam{" +
        "id=" + id +
        ", name=" + name +
        ", value=" + value +
        ", enable=" + enable +
        ", description=" + description +
        ", parentId=" + parentId +
        ", type=" + type +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
