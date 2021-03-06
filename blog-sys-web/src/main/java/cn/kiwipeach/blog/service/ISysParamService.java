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

import cn.kiwipeach.blog.domain.SysParam;
import cn.kiwipeach.blog.domain.vo.FriendLinkVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 系统参数 服务接口类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
public interface ISysParamService extends IService<SysParam> {

    @Cacheable(value = {"FRIEND_LINKlIST"}, key = "'dataSet'")
    List<FriendLinkVO> queryFriendListLink();
}
