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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.domain.vo.SysBannerInvoVO;
import cn.kiwipeach.blog.mapper.BlogCategoryMapper;
import cn.kiwipeach.blog.mapper.BlogMapper;
import cn.kiwipeach.blog.mapper.BlogTagMapper;
import cn.kiwipeach.blog.mapper.CommentReplyMapper;
import cn.kiwipeach.blog.service.IBlogCommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 描述：系统功能通用接口服务实现类
 *
 * @author kiwipeach
 * @create 2019-04-02
 */
@Service
public class BlogCommServiceImpl implements IBlogCommService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogTagMapper blogTagMapper;
    @Autowired
    private BlogCategoryMapper blogCategoryMapper;
    @Autowired
    private CommentReplyMapper commentReplyMapper;

    @Override
    public SysBannerInvoVO querySysBannerInfo() {
        Integer blogCount = blogMapper.selectCount(null);
        Integer tagCount = blogTagMapper.selectCount(null);
        Integer categoryCount = blogCategoryMapper.selectCount(null);
        Integer commentReplyCount = commentReplyMapper.selectCount(null);
        LocalDateTime now = LocalDateTime.now();
        TextStyle style = TextStyle.SHORT;
        Locale locale = Locale.CHINA;
        String formatDateTime = String.format("%s年%s月%s日 %s时%s分%s秒 %s", now.getYear(), now.getMonth().getValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond(), now.getDayOfWeek().getDisplayName(style,locale));
        return new SysBannerInvoVO(blogCount, tagCount, categoryCount, commentReplyCount, formatDateTime);
    }
}
