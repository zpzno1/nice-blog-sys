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
package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.domain.vo.SysBannerInvoVO;

/**
 * 描述：博客通用服务实现类
 *
 * @author kiwipeach
 * @create 2019-04-02
 */
public interface IBlogCommService {

    /**
     * 查询系统banner公共信息
     *
     * @return 返回系统公共banner信息
     */
    SysBannerInvoVO querySysBannerInfo();

}
