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
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 系统用户
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("SYS_USER")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号（序列生成，可以观看会员注册先后）
     */
    @TableId("ID")
    private String id;

    /**
     * 三方账号ID（三方用户编号）
     */
    @TableField("THIRD_USER_ID")
    private String thirdUserId;

    /**
     * 登录账号(三方登陆账号)
     */
    @TableField("USER_NAME")
    private String userName;

    /**
     * 密码(本站注册密码，只有注册或者三方登陆完善过信息的用户才有)
     */
    @TableField("PASSWORD")
    private String password;

    /**
     * 用户昵称（默认取三方平台昵称，或者登陆名称,本地系统则使用博客用户码）
     */
    @TableField("NICK_NAME")
    private String nickName;

    /**
     * 用户头像 (默认三方平台头像，可后期自行更换头像,本地系统使用默认头像)
     */
    @TableField("HEAD_URL")
    private String headUrl;

    /**
     * 用户邮箱 (用户信息完善时候提供)
     */
    @TableField("EMAIL")
    private String email;

    /**
     * 账号是否锁定[0:正常 1:锁定] （对于一些非法账号的控制）
     */
    @TableField("ACCOUNT_LOCK")
    private Integer accountLock;

    /**
     * 账号锁定原因[1:不当言论](改变账号状态的理由)
     */
    @TableField("LOCK_REASON")
    private BigDecimal lockReason;

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
    public String getThirdUserId() {
        return thirdUserId;
    }

    public void setThirdUserId(String thirdUserId) {
        this.thirdUserId = thirdUserId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getAccountLock() {
        return accountLock;
    }

    public void setAccountLock(Integer accountLock) {
        this.accountLock = accountLock;
    }
    public BigDecimal getLockReason() {
        return lockReason;
    }

    public void setLockReason(BigDecimal lockReason) {
        this.lockReason = lockReason;
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
        return "SysUser{" +
        "id=" + id +
        ", thirdUserId=" + thirdUserId +
        ", userName=" + userName +
        ", password=" + password +
        ", nickName=" + nickName +
        ", headUrl=" + headUrl +
        ", email=" + email +
        ", accountLock=" + accountLock +
        ", lockReason=" + lockReason +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
