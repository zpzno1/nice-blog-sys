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

import cn.kiwipeach.blog.domain.BlogCategory;
import cn.kiwipeach.blog.domain.vo.CategoryTreeVO;
import cn.kiwipeach.blog.mapper.BlogCategoryMapper;
import cn.kiwipeach.blog.service.IBlogCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 分类 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@Service
public class BlogCategoryServiceImpl extends ServiceImpl<BlogCategoryMapper, BlogCategory> implements IBlogCategoryService {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public List<CategoryTreeVO> queryBlogCategoryTree() {
        List<CategoryTreeVO> categoryTreeVOS = blogCategoryMapper.selectCategoryTree();
        //合并树形分类中的叶子的统计和名称
        for (CategoryTreeVO categoryTreeVO : categoryTreeVOS) {
            if (!StringUtils.isEmpty(categoryTreeVO.getCount())) {
                categoryTreeVO.setName(categoryTreeVO.getName() + "(" + categoryTreeVO.getCount() + ")");
            }
        }
        return categoryTreeVOS;
    }
}
