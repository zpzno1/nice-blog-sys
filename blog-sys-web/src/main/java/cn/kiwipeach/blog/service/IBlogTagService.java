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

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.BlogTag;
import cn.kiwipeach.blog.domain.vo.TagCountVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 标签 服务接口类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
public interface IBlogTagService extends IService<BlogTag> {

    @Cacheable(value = {"INDEX_TAG"}, key = "'tag_count'")
    AjaxResponse<List<TagCountVO>> queryTagCountInfo();

}
