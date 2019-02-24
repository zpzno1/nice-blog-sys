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
import java.time.LocalDateTime;

/**
 * 博客
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
@TableName("T_BLOG")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客编号
     */
    @TableId("ID")
    private String id;

    /**
     * 用户编号
     */
    @TableField("USER_ID")
    private String userId;

    /**
     * 分类编号
     */
    @TableField("CATE_ID")
    private String cateId;

    /**
     * 博客标题
     */
    @TableField("TITLE")
    private String title;

    /**
     * 博客内容（根据内容类型存放不同的内容数据）
     */
    @TableField("CONTENT")
    private String content;

    /**
     * 博客点赞
     */
    @TableField("STAR_COUNT")
    private Integer starCount;

    /**
     * 浏览总量
     */
    @TableField("VIEWS")
    private Integer views;

    /**
     * 是否置顶
     */
    @TableField("TOP")
    private Integer top;

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
     * 博客内容类型[0:千牛markdown类型 1:网页元素自定义类型]
     */
    @TableField("CONTENT_TYPE")
    private String contentType;

    /**
     * 博客简介
     */
    @TableField("INTRODUCTION")
    private String introduction;

    /**
     * 博客图标
     */
    @TableField("ICON_URL")
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStarCount() {
        return starCount;
    }

    public void setStarCount(Integer starCount) {
        this.starCount = starCount;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", cateId=" + cateId +
                ", title=" + title +
                ", content=" + content +
                ", starCount=" + starCount +
                ", views=" + views +
                ", top=" + top +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", contentType=" + contentType +
                ", introduction=" + introduction +
                ", iconUrl=" + iconUrl +
                "}";
    }

}
