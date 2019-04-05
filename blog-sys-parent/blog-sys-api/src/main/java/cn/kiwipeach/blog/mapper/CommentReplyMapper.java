/*
 * Copyright 2019 kiwipeach[1099501218@qq.com].
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
package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.domain.CommentReply;
import cn.kiwipeach.blog.domain.vo.BlogCommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论 Mapper 接口
 *
 * @author kiwipeach
 * @create 2019-03-18
 */
public interface CommentReplyMapper extends BaseMapper<CommentReply> {

    /**
     * 分页查询博客的评论信息
     *
     * @param page     分页入参
     * @param parentId 父节点信息
     * @param type     查询类型
     * @return 返回分页评论信息
     */
    List<BlogCommentVO> selectCommenByPage(IPage<BlogCommentVO> page,
                                           @Param("parentId") String parentId,
                                           @Param("type") String type);

    /**
     * 更新评论回复的点赞数量
     *
     * @param commentId 评论对象
     * @return 返回更新记录行数
     */
    Integer updateCommentStarCount(@Param("commentId") String commentId);

}
