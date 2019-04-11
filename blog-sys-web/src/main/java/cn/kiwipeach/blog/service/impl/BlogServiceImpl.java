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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import cn.kiwipeach.blog.domain.vo.TagVO;
import cn.kiwipeach.blog.mapper.BlogArchiveMapper;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.BlogTagMapper;
import cn.kiwipeach.blog.service.adapter.BlogServiceAdapter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 博客 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@Service
public class BlogServiceImpl extends BlogServiceAdapter {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private BlogArchiveMapper blogArchiveMapper;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public IPage<BlogInfoVO> pageQuery(IPage<BlogInfoVO> page, String categoryId, String tagName) {
        List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page, categoryId, tagName);
        for (BlogInfoVO blogInfoVO : blogInfoVOS) {
            dealTagsName(blogInfoVO);
        }
        return page.setRecords(blogInfoVOS);
    }

    @Override
    public BlogInfoVO queryById(String blogId) {
        BlogInfoVO blogInfoVO = blogMapper.selectBlog(blogId);
        dealTagsName(blogInfoVO);
        Page<Blog> page = new Page<>(1, 1);
        Blog nextBlog = blogMapper.selectNextBlog(page, blogId);
        // 处理最后一篇博客下一篇问题
        if (nextBlog == null) {
            QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().eq("top", "1");
            nextBlog = blogMapper.selectOne(queryWrapper);
        }
        Blog previousBlog = blogMapper.selectPreviousBlog(page, blogId);
        // 处理第一篇博客上一篇问题
        if (previousBlog == null) {
            QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().eq("top", "1");
            previousBlog = blogMapper.selectOne(queryWrapper);
        }
        blogInfoVO.setNextBlog(nextBlog);
        blogInfoVO.setPreviousBlog(previousBlog);
        return blogInfoVO;
    }

    @Override
    public IPage<ArchiveBlogTimelineVO> archiveBlogQuery(IPage<ArchiveBlogTimelineVO> page, String pattern) {
        List<ArchiveBlogTimelineVO> archiveBlogTimelineVOS = queryArchiveBlogByPattern(page,pattern);
        //给分页查询结果添加标签
        for (ArchiveBlogTimelineVO tagVOS : archiveBlogTimelineVOS) {
            List<TagVO> tagVOList = blogTagMapper.selectBlogTag(tagVOS.getId());
            tagVOS.setTagVOList(JSONArray.parseArray(JSON.toJSONString(tagVOList)));
        }
        return page.setRecords(archiveBlogTimelineVOS);
    }

    /**
     * 将前端传过来的日期类型转换成MySql本地语法
     *
     * @param pattern 前端日期
     * @return 返回mysql日期表示
     */
    private List<ArchiveBlogTimelineVO> queryArchiveBlogByPattern(IPage<ArchiveBlogTimelineVO> page,String pattern) {
        if ("yyyy".equals(pattern)) {
            return blogArchiveMapper.selectArchiveBlogByYear(page);
        } else if ("yyyyQ".equals(pattern)) {
            return blogArchiveMapper.selectArchiveBlogByYearQuarter(page);
        } else if ("yyyyMM".equals(pattern)) {
            return blogArchiveMapper.selectArchiveBlogByYearMonth(page);
        } else {
            throw new IllegalArgumentException("非法参数异常:" + pattern);
        }
    }

    @Override
    public AjaxResponse<Boolean> createBlogAgree(String blogId) {
        //如果缓存中存在点赞数据，则不允许重复点赞
        String cacheKey = new StringBuffer("BLOG_STAR_COUNT::blog_id_" + blogId).toString();
        if (Objects.isNull(valueOperations.get(cacheKey))) {
            Integer integer = blogMapper.updateBlogStarCount(blogId);
            if (integer > 0) {
                valueOperations.set(cacheKey, 1, 2, TimeUnit.MINUTES);
                return AjaxResponse.success(true);
            } else {
                return AjaxResponse.fail("-BLOG_STAR_001", "博客点赞失败");
            }
        } else {
            return AjaxResponse.fail("-BLOG_STAR_001", "您近期点过赞");
        }

    }

    /**
     * 为博客信息附加上标签信息
     *
     * @param blogInfoVO 目标博客对象
     */
    private void dealTagsName(BlogInfoVO blogInfoVO) {
        // 处理博客标签
        List<TagVO> tagVOS = blogTagMapper.selectBlogTag(blogInfoVO.getId());
        blogInfoVO.setBlogTagList(tagVOS);
        // 处理博客图片(需要登录)
        //String download = iMarkdownStoreageService.download(blogInfoVO.getContent());
        //blogInfoVO.setContent(download);
    }
}
