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
package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.domain.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mybatis-plus案例相关操作
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/11
 */
public interface DemoMapper extends BaseMapper<Blog> {

    /**
     * 测试分页查询，需要在service才能够体现分页效果
     */
    List<Blog> selectBlogListDemo(Page<Blog> page, @Param("userId") String userId);



}
