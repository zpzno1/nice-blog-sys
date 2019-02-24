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
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.service.IBlogService;
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

    @Override
    public boolean createBlog(Blog blog) {
        Integer insert = blogMapper.insert(blog);
        return insert > 0;
    }

    @Override
    public IPage<BlogInfoVO> pageQuery(IPage<BlogInfoVO> page) {
        List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page);
        return page.setRecords(blogInfoVOS);
    }
}
