/*
 * Copyright 2019 liuburu@qq.com.
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
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.service.demo.IEhcacheDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 描述：ehcache服务接口实现类
 * <p>
 * Cacheable : Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中。
 * CacheEvict : 清除缓存。
 * CachePut : @CachePut也可以声明一个方法支持缓存功能。使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中。
 *
 * @author kiwipeach
 * @create 2019-03-29
 */
@Service
//@CacheConfig(cacheNames = {"INDEX_BLOG_CACHE"})
@Slf4j
public class EhcacheDemoServiceImpl implements IEhcacheDemoService {

    @Autowired
    private BlogMapper blogMapper;


    @Override
    public Blog save(Blog blog) {
        Integer insertRow = blogMapper.insert(blog);
        log.info("新增功能，同步到缓存，直接写入数据库，ID为：{},插入结果：{}", blog.getId(), insertRow);
        return blog;
    }

    @Override
    public Blog selectById(String blogId) {
        log.info("查询功能，缓存未找到，直接读取数据库，ID为：" + blogId);
        return blogMapper.selectById(blogId);
    }

    @Override
    public Blog updateById(Blog blog) {
        blogMapper.updateById(blog);
        log.info("更新功能，更新缓存，直接更新数据库，ID为：" + blog.getId());
        return blog;
    }

    @Override
    public boolean deleteById(String blogId) {
        log.info("删除功能，删除缓存，直接删除数据库数据，ID为：" + blogId);
        Integer integer = blogMapper.deleteById(blogId);
        return integer > 0;
    }


}
