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
package cn.kiwipeach.blog.domain.vo;

import cn.kiwipeach.blog.domain.Blog;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.List;

/**
 * 博客信息实体
 *
 * @author kiwipeach
 * @create 2019-02-24
 */
@Getter
@Setter
public class BlogInfoVO implements Serializable {
    private static final long serialVersionUID = -1076318301346864686L;
    private String id;
    private String userId;
    private String nickName;
    private String cateId;
    private String categoryName;
    private String title;
    //影像页面加载速度
    //private String content;
    private String starCount;
    private String commentCount;
    private String viewCount;
    private String top;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private String updateTime;
    private String contentType;
    private String introduction;
    private String iconUrl;
    /**
     * 博客标签
     */
    private List<TagVO> blogTagList;

}
