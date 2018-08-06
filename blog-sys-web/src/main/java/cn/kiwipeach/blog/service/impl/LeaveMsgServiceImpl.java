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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.domain.LeaveMsg;
import cn.kiwipeach.blog.mapper.LeaveMsgMapper;
import cn.kiwipeach.blog.service.ILeaveMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 留言 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Service
public class LeaveMsgServiceImpl extends ServiceImpl<LeaveMsgMapper, LeaveMsg> implements ILeaveMsgService {

}
