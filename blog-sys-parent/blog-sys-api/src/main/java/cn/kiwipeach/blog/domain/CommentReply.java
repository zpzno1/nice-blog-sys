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

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

/**
 * 评论
 *
 * @author kiwipeach
 * @create 2019-03-18
 */
@TableName("T_COMMENT_REPLY")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论编号
     */
    @TableId("ID")
    private String id;

    /**
     * 评论回复类型[BLOG_COMMENT,BLOG_REPLY,LAM_COMMENT,LAM_REPLY]
     */
    @TableField("TYPE")
    private String type;

    /**
     * 父亲节点
     */
    @TableField("PARENT_ID")
    private String parentId;

    /**
     * 主动评论或者回复的人
     */
    @TableField("ACTIVE_USER_ID")
    private String activeUserId;

    /**
     * 被评论或者回复的人
     */
    @TableField("PASSIVE_USER_ID")
    private String passiveUserId;

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

    /**
     * 评论回复点赞数量
     */
    @TableField("STAR_COUNT")
    private Integer starCount;

    /**
     * 回复统计（在评论中回复需要对该字段+1）
     */
    @TableField("REPLY_COUNT")
    private Integer replyCount;

    public CommentReply() {
    }

    public CommentReply(String type, String parentId, String content) {
        this.type = type;
        this.parentId = parentId;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getActiveUserId() {
        return activeUserId;
    }

    public void setActiveUserId(String activeUserId) {
        this.activeUserId = activeUserId;
    }

    public String getPassiveUserId() {
        return passiveUserId;
    }

    public void setPassiveUserId(String passiveUserId) {
        this.passiveUserId = passiveUserId;
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

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    @Override
    public String toString() {
        return "CommentReply{" +
                "id=" + id +
                ", type=" + type +
                ", parentId=" + parentId +
                ", activeUserId=" + activeUserId +
                ", passiveUserId=" + passiveUserId +
                ", content=" + content +
                ", deleted=" + deleted +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", starCount=" + starCount +
                ", replyCount=" + replyCount +
                "}";
    }
}
