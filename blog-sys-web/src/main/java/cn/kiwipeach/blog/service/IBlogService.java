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
package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 博客 服务接口类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
public interface IBlogService extends IService<Blog> {
    /**
     * 分页查询博客信息
     * @param page 分页信息
     * @return 博客列表
     */
    IPage<BlogInfoVO> pageQuery(IPage<BlogInfoVO> page);

}
