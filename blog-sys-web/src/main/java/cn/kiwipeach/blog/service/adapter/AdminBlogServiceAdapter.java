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
package cn.kiwipeach.blog.service.adapter;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 后台博客发表适配器
 *
 * @author kiwipeach
 * @create 2019-03-04
 */
public abstract class AdminBlogServiceAdapter  extends ServiceImpl<BlogMapper, Blog> implements IBlogService  {

    @Override
    public AjaxResponse<IPage<BlogInfoVO>> pageQuery(IPage<BlogInfoVO> page, String categoryId, String tagName) {
        return null;
    }

    @Override
    public BlogInfoVO queryById(String blogId) {
        return null;
    }

    @Override
    public AjaxResponse<IPage<ArchiveBlogTimelineVO>> archiveBlogQuery(IPage<ArchiveBlogTimelineVO> page, String pattern) {
        return null;
    }

    @Override
    public AjaxResponse<Boolean> createBlogAgree(String blogId) {
        return null;
    }
}
