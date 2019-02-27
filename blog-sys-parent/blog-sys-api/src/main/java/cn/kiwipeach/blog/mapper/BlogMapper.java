/*
 * Copyright 2019 kiwipeach[1099501218@qq.com].
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
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.NonNull;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 博客 Mapper 接口
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
public interface BlogMapper extends BaseMapper<Blog> {
    /**
     * 分页查询博客信息
     *
     * @param page 分页对象
     * @return 返回博客分页列表
     */
    List<BlogInfoVO> selectByPage(IPage<BlogInfoVO> page);

    /**
     * 查询单条博客详情
     *
     * @param blogId 博客编号
     * @return 返回博客详情信息
     */
    BlogInfoVO selectBlog(String blogId);

    /**
     *  查询上一篇博客
     * @param blogId 目标博客编号
     * @return 返回博客信息
     */
    Blog selectPreviousBlog(@Param("blogId") String blogId);

   /**
     *  查询上一篇博客
     * @param blogId 目标博客编号
     * @return 返回博客信息
     */
    Blog selectNextBlog(@Param("blogId") String blogId);

}
