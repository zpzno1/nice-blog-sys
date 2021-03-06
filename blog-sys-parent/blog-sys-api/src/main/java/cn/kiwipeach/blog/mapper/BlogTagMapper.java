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
package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.domain.BlogTag;
import cn.kiwipeach.blog.domain.vo.TagCountVO;
import cn.kiwipeach.blog.domain.vo.TagVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 标签 Mapper 接口
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
public interface BlogTagMapper extends BaseMapper<BlogTag> {

    /**
     * 查询单条博客的所有标签
     *
     * @param blogId 博客编号
     * @return 返回博客标签信息
     */
    List<TagVO> selectBlogTag(String blogId);

    /**
     * 查询所有的博客统计情况
     *
     * @return 返回所有的标签统计情况
     */
    List<TagCountVO> selectTagCountInfo();

}
