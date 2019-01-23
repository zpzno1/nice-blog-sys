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
 * 评论
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("T_COMMENT_MSG")
public class CommentMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论编号
     */
    @TableId("ID")
    private String id;

    /**
     * 评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)类型
     */
    @TableField("PARENT_COMMENT_TYPE")
    private String parentCommentType;

    /**
     * 评论回复对象(0：博客一级评论,1：博客二级评论 2：留言一级评论 3：留言二级评论)编号
     */
    @TableField("TARGET_ID")
    private String targetId;

    /**
     * 角色A
     */
    @TableField("USER_AID")
    private String userAid;

    /**
     * 角色B
     */
    @TableField("USER_BID")
    private String userBid;

    /**
     * 评论内容
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 该评论留言是否删除[0:正常 1:删除]
     */
    @TableField("DELETED")
    private Integer deleted;

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
    public String getParentCommentType() {
        return parentCommentType;
    }

    public void setParentCommentType(String parentCommentType) {
        this.parentCommentType = parentCommentType;
    }
    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
    public String getUserAid() {
        return userAid;
    }

    public void setUserAid(String userAid) {
        this.userAid = userAid;
    }
    public String getUserBid() {
        return userBid;
    }

    public void setUserBid(String userBid) {
        this.userBid = userBid;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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
        return "CommentMsg{" +
        "id=" + id +
        ", parentCommentType=" + parentCommentType +
        ", targetId=" + targetId +
        ", userAid=" + userAid +
        ", userBid=" + userBid +
        ", content=" + content +
        ", deleted=" + deleted +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
