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

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import cn.kiwipeach.blog.domain.vo.TagVO;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.BlogTagMapper;
import cn.kiwipeach.blog.service.IBlogService;
import cn.kiwipeach.blog.service.IMarkdownStoreageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private BlogTagMapper blogTagMapper;

    @Autowired
    private IMarkdownStoreageService iMarkdownStoreageService;

    @Override
    public IPage<BlogInfoVO> pageQuery(IPage<BlogInfoVO> page, String categoryId, String tagName) {
        List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page, categoryId, tagName);
        for (BlogInfoVO blogInfoVO : blogInfoVOS) {
            // 处理博客标签
            List<TagVO> tagVOS = blogTagMapper.selectBlogTag(blogInfoVO.getId());
            blogInfoVO.setBlogTagList(tagVOS);
            // 处理博客图片
            String download = iMarkdownStoreageService.download(blogInfoVO.getContent());
            blogInfoVO.setContent(download);
        }
        return page.setRecords(blogInfoVOS);
    }

    @Override
    public BlogInfoVO queryById(String blogId) {
        BlogInfoVO blogInfoVO = blogMapper.selectBlog(blogId);
        // 处理博客标签（例外加载）
        List<TagVO> tagVOS = blogTagMapper.selectBlogTag(blogInfoVO.getId());
        blogInfoVO.setBlogTagList(tagVOS);
        // 处理博客图片(需要登录)
        String download = iMarkdownStoreageService.download(blogInfoVO.getContent());
        blogInfoVO.setContent(download);
        // TODO 添加博客上一页下一页的地址信息，如果到达边界那么就显示置顶文章内容
        Blog nextBlog = blogMapper.selectNextBlog(blogId);
        // 处理最后一篇博客下一篇问题
        if (nextBlog == null) {
            QueryWrapper<Blog> queryWrapper = new QueryWrapper<Blog>().eq("top", "1");
            nextBlog = blogMapper.selectOne(queryWrapper);
        }
        Blog previousBlog = blogMapper.selectPreviousBlog(blogId);
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
        List<ArchiveBlogTimelineVO> archiveBlogTimelineVOS = blogMapper.selectArchiveBlog(page, pattern);
        //给分页查询结果添加标签
        for (ArchiveBlogTimelineVO tagVOS : archiveBlogTimelineVOS) {
            List<TagVO> tagVOList = blogTagMapper.selectBlogTag(tagVOS.getId());
            tagVOS.setTagVOList(tagVOList);
        }
        return page.setRecords(archiveBlogTimelineVOS);
    }
}
