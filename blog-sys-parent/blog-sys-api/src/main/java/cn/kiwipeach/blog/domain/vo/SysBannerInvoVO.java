/*
 * Copyright 2019 liuburu@qq.com.
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

import lombok.*;

/**
 * 描述：系统头部统计信息
 *
 * @author kiwipeach
 * @create 2019-04-02
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SysBannerInvoVO {
    /**
     * 博客总量
     */
    private Integer blogCount;
    /**
     * 标签总量
     */
    private Integer tagCount;
    /**
     * 分类总量
     */
    private Integer categoryCount;
    /**
     * 评论回复总量
     */
    private Integer commentReplyCount;
    /**
     * 当前格式化日期时间
     */
    private String formateNowDate;
}
