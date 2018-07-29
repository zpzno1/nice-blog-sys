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

import cn.kiwipeach.blog.domain.RTagBlog;
import cn.kiwipeach.blog.mapper.RTagBlogMapper;
import cn.kiwipeach.blog.service.IRTagBlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 标签-博客对照表 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-07-29
 */
@Service
public class RTagBlogServiceImpl extends ServiceImpl<RTagBlogMapper, RTagBlog> implements IRTagBlogService {

}
