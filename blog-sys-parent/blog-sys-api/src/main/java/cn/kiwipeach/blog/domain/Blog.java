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
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 博客
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@TableName("T_BLOG")
public class Blog extends Model<Blog> implements Serializable {

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
     * 博客内容
     */
    @TableField("CONTENT_KEY")
    private String contentKey;

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

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
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

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", userId=" + userId +
                ", cateId=" + cateId +
                ", title=" + title +
                ", contentKey=" + contentKey +
                ", starCount=" + starCount +
                ", views=" + views +
                ", top=" + top +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
