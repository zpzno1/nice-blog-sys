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
import cn.kiwipeach.blog.mapper.DemoMapper;
import cn.kiwipeach.blog.service.IDemoService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 博客 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Service
@Slf4j
public class DemoServiceImpl extends ServiceImpl<DemoMapper, Blog> implements IDemoService {

    @Override
    public Page<Blog> selectBlogPage(Page<Blog> page, String userId){
        return page.setRecords(baseMapper.selectBlogList(page,userId));
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
