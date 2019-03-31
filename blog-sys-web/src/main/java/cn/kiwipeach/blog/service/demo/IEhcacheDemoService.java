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
package cn.kiwipeach.blog.service.demo;

import cn.kiwipeach.blog.domain.Blog;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * 描述：ehcache测试服务接口
 *
 * @author kiwipeach
 * @create 2019-03-29
 */
public interface IEhcacheDemoService {

    /**
     * 保存博客同时缓存博客信息
     *
     * @param blog 博客对象
     * @return 返回博客缓存对象
     */
    @CachePut(cacheNames = {"DEMO_CACHE"},key = "'sysuser_id_'+#blog.id")
    Blog save(Blog blog);

    /**
     * 查询博客，优先查询缓存，缓存中没有就读取数据库
     *
     * @param blogId 博客编号
     * @return 返回博客信息
     */
    @Cacheable(cacheNames = {"DEMO_CACHE"},key = "'blog_id_'+#blogId")
    Blog selectById(String  blogId);

    /**
     * 更新博客信息，同时更新缓存
     *
     * @param Blog 博客对象
     * @return 更新博客信息
     */
    @CachePut(cacheNames = {"DEMO_CACHE"},key = "'blog_id_'+#blog.id")
    Blog updateById(Blog Blog);

    /**
     * 删除博客信息，同时删除缓存
     *
     * @param blogId 博客编号
     * @return 返回删除结果
     */
    @CacheEvict(cacheNames = {"DEMO_CACHE"},key = "'blog_id_'+#blogId")
    boolean deleteById(String blogId);
}
