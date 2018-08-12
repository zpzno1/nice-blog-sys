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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.DemoMapper;
import cn.kiwipeach.blog.service.IDemoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 博客 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Service
@Slf4j
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Blog> implements IDemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public Page<Blog> queryBlogListDemo(Page<Blog> page, String userId) {
        List<Blog> blogs1 = demoMapper.selectBlogListDemo(page, userId);
        List<Blog> blogs2 = baseMapper.selectBlogListDemo(page, userId);
        return (Page<Blog>) page.setRecords(blogs2);
    }

    @Override
    public void testTranactional(Blog blog) {
        Integer updateRow = baseMapper.updateById(blog);
        log.info("博客标题更新结果:{}", updateRow);
        if (true) {
            throw new BlogException(1001, "博客异常");
        }
    }
}
