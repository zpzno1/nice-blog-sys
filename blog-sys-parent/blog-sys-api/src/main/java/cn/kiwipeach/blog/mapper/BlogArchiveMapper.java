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
package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：博客归档接口
 *
 * @author kiwipeach
 * @create 2019-04-11
 */
public interface BlogArchiveMapper {


    List<ArchiveBlogTimelineVO> selectArchiveBlogByYear(IPage<ArchiveBlogTimelineVO> page);

    List<ArchiveBlogTimelineVO> selectArchiveBlogByYearQuarter(IPage<ArchiveBlogTimelineVO> page);

    List<ArchiveBlogTimelineVO> selectArchiveBlogByYearMonth(IPage<ArchiveBlogTimelineVO> page);

}
