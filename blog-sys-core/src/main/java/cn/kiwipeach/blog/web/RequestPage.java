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
package cn.kiwipeach.blog.web;

/**
 * 分页请求对象
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/15
 */
public class RequestPage {
    private static final Integer DEFAULT_PAGESIZE = 10;
    private static final Integer DEFALT_PAGENO = 1;
    /**
     * 分页大小
     */
    private Integer pageSize = DEFAULT_PAGESIZE;
    /**
     * 分页当前页
     */
    private Integer pageNo = DEFALT_PAGENO;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
