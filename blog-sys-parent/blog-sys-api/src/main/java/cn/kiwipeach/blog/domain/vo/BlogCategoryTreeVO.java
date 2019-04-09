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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 分类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@Getter
@Setter
@ToString
public class BlogCategoryTreeVO implements Serializable {
    /**
     * 分类编号
     */
    private String id;
    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类同级权重
     */
    private Integer weight;
    /**
     * 父类分类编号
     */
    private String parentId;

    /**
     * 子节点信息
     */
    private List<BlogCategoryTreeVO> blogCategoryTreeVOList;

}
