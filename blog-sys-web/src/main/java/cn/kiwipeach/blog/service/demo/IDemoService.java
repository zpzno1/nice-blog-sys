/*
 * Copyright 2019 kiwipeach.
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 博客 服务接口类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
public interface IDemoService extends IService<Blog> {

    /**
     * 测试mybatis分页
     *
     * @param page
     * @param userId
     * @return
     */
    IPage<Blog> queryBlogListDemo(IPage<Blog> page, String userId);

    /**
     * 事务测试服务
     */
    @Transactional
    void testTranactional(Blog blog);

    /**
     * 测试运行时异常
     */
    void testRuntimeException();


}
