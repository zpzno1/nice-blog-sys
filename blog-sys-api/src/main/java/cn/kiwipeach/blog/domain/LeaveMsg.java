/*
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
package cn.kiwipeach.blog.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
/**
 * 留言
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@TableName("T_LEAVE_MSG")
public class LeaveMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 留言编号
     */
    @TableId("ID")
    private String id;

    /**
     * 角色A
     */
    @TableField("ROLE_AID")
    private String roleAid;

    /**
     * 角色B
     */
    @TableField("ROLE_BID")
    private String roleBid;

    /**
     * 留言目标
     */
    @TableField("TARGET_ID")
    private String targetId;

    /**
     * 留言目标类型[0:博主,1:回复]
     */
    @TableField("TARGET_ID_TYPE")
    private BigDecimal targetIdType;

    /**
     * 留言内容
     */
    @TableField("LEAVE_MSG")
    private String leaveMsg;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRoleAid() {
        return roleAid;
    }

    public void setRoleAid(String roleAid) {
        this.roleAid = roleAid;
    }
    public String getRoleBid() {
        return roleBid;
    }

    public void setRoleBid(String roleBid) {
        this.roleBid = roleBid;
    }
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    public BigDecimal getTargetIdType() {
        return targetIdType;
    }

    public void setTargetIdType(BigDecimal targetIdType) {
        this.targetIdType = targetIdType;
    }
    public String getLeaveMsg() {
        return leaveMsg;
    }

    public void setLeaveMsg(String leaveMsg) {
        this.leaveMsg = leaveMsg;
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
        return "LeaveMsg{" +
        "id=" + id +
        ", roleAid=" + roleAid +
        ", roleBid=" + roleBid +
        ", targetId=" + targetId +
        ", targetIdType=" + targetIdType +
        ", leaveMsg=" + leaveMsg +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
