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



import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 测试mybatis-plus的AR操作
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@TableName("T_BLOG")
public class ARBlog extends Model<ARBlog> {

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
    @TableField("CONTENT")
    private String content;

    /**
     * 博客点赞
     */
    @TableField("STAR_COUNT")
    private BigDecimal starCount;

    /**
     * 浏览总量
     */
    @TableField("VIEWS")
    private BigDecimal views;

    /**
     * 是否置顶
     */
    @TableField("TOP")
    private BigDecimal top;

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public BigDecimal getStarCount() {
        return starCount;
    }

    public void setStarCount(BigDecimal starCount) {
        this.starCount = starCount;
    }
    public BigDecimal getViews() {
        return views;
    }

    public void setViews(BigDecimal views) {
        this.views = views;
    }
    public BigDecimal getTop() {
        return top;
    }

    public void setTop(BigDecimal top) {
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
        ", content=" + content +
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
