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
package cn.kiwipeach.blog.service.demo.impl;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.exception.BlogException;
import cn.kiwipeach.blog.mapper.DemoMapper;
import cn.kiwipeach.blog.service.demo.IDemoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    public IPage<Blog> queryBlogListDemo(IPage<Blog> page, String userId) {
        List<Blog> blogs1 = demoMapper.selectBlogListDemo(page, userId);
        List<Blog> blogs2 = baseMapper.selectBlogListDemo(page, userId);
        return page.setRecords(blogs2);
    }

    @Override
    public void testTranactional(Blog blog) {
        Integer updateRow = baseMapper.updateById(blog);
        log.info("博客标题更新结果:{}", updateRow);
        if (true) {
            throw new BlogException("-BLOG_TEST", "博客异常");
        }
    }

    @Override
    public void testRuntimeException() {
        if (true) {
            throw new BlogException("-BLOG_TEST", "测试博客运行异常");
        }
    }
}
