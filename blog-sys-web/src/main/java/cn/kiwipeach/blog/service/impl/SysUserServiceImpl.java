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
package cn.kiwipeach.blog.service.impl;

import cn.kiwipeach.blog.domain.RUserRole;
import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.mapper.RUserRoleMapper;
import cn.kiwipeach.blog.mapper.SysUserMapper;
import cn.kiwipeach.blog.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统用户 服务实现类
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2019-01-24
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RUserRoleMapper rUserRoleMapper;

    @Override
    public boolean createSysUser(AccessToken accessToken) {
        accessToken.setUserName(accessToken.getPlatform() + "_" + accessToken.getUserName());
        //1)保存基本用户信息
        Integer insert1 = sysUserMapper.insert(accessToken);
        RUserRole role = new RUserRole();
        role.setRoleId("1001");
        role.setUserId(accessToken.getId());
        //2）保存默认用户角色信息
        Integer insert2 = rUserRoleMapper.insert(role);
        if (insert1 > 0 && insert2 > 0) {
            return true;
        } else {
            return false;
        }
    }
}
