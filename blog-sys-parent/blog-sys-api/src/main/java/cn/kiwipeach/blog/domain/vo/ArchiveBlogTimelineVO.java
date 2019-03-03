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

import java.util.List;

/**
 * 归档博客实体类
 *
 * @author kiwipeach
 * @create 2019-03-03
 */
@Getter
@Setter
public class ArchiveBlogTimelineVO {

    private String id;
    private String title;
    private String categoryName;
    private String createTime;
    /**
     * 归档时间
     */
    private String archiveTime;
    /**
     * 是否为归档范围内的第一个节点
     */
    private String isTop;
    /**
     * 所有的博客标签，程序后面赋值
     */
    private List<TagVO> tagVOList;
}
