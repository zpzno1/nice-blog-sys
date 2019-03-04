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

import cn.kiwipeach.blog.configuration.AjaxResponse;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博客 服务接口类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
public interface IBlogService extends IService<Blog> {
    /*##################################################前台##################################################*/

    /**
     * 分页查询博客信息
     *
     * @param page       分页信息
     * @param categoryId 分类编号
     * @param tagName    标签名称
     * @return 博客列表
     */
    IPage<BlogInfoVO> pageQuery(IPage<BlogInfoVO> page, String categoryId, String tagName);

    /**
     * 查询博客单条记录
     *
     * @param blogId 博客编号
     * @return 返回博客信息
     */
    BlogInfoVO queryById(String blogId);


    /**
     * 查询博客归档信息
     *
     * @param page    分页信息
     * @param pattern 归档格式
     * @return 返回归档博客
     */
    IPage<ArchiveBlogTimelineVO> archiveBlogQuery(IPage<ArchiveBlogTimelineVO> page, String pattern);

    /*##################################################后台##################################################*/

    /**
     * 上传博客文件
     *
     * @param multipartFile 目标博客
     * @return 上传博客状态
     */
    AjaxResponse<Boolean> uploadBlog(MultipartFile multipartFile);


}
