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
package cn.kiwipeach.blog.mapper;


import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoDetailVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客 Mapper 接口
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 分页查询博客信息
     *
     * @param page       分页对象
     * @param categoryId 分类编号
     * @param tagName    标签名称
     * @return 返回博客分页列表
     */
    List<BlogInfoVO> selectByPage(IPage<BlogInfoVO> page,
                                  @Param("categoryId") String categoryId,
                                  @Param("tagName") String tagName);

    /**
     * 查询单条博客详情
     *
     * @param blogId 博客编号
     * @return 返回博客详情信息
     */
    BlogInfoDetailVO selectBlog(String blogId);

    /**
     * 查询上一篇博客
     *
     * @param blogId 目标博客编号
     * @return 返回博客信息
     */
    Blog selectPreviousBlog(IPage<Blog> page,@Param("blogId") String blogId);

    /**
     * 查询上一篇博客
     *
     * @param blogId 目标博客编号
     * @return 返回博客信息
     */
    Blog selectNextBlog(IPage<Blog> page,@Param("blogId") String blogId);

    /**
     * 查询按照时间归档博客信息
     *
     * @param page    分页对象
     * @param pattern 归档格式 主要有三种:yyyy,yyyyMM,yyyyQ
     * @return 返回归档博客信息
     */
    List<ArchiveBlogTimelineVO> selectArchiveBlog(IPage<ArchiveBlogTimelineVO> page, @Param("pattern") String pattern);

    /**
     * 博客评论记录总数+1操作
     *
     * @param blogId 博客编号
     * @return 返回更新结果
     */
    Integer updateCommentCountByBlogId(@Param("blogId") String blogId);

    /**
     * 更新博客浏览量
     *
     * @param blogId 博客编号
     * @return 返回博客浏览量
     */
    Integer updateBlogViewCountByBlogId(@Param("blogId") String blogId);

    /**
     * 更新博客点赞数量
     *
     * @param blogId 博客编号
     * @return 返回更新结果
     */
    Integer updateBlogStarCount(@Param("blogId") String blogId);


}
