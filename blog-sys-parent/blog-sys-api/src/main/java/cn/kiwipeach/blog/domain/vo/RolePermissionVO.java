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

import cn.kiwipeach.blog.domain.SysPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色权限信息
 *
 * @author kiwipeach
 * @create 2019-03-17
 */
@Getter
@Setter
public class RolePermissionVO {
    private String roleId;
    private String roleName;
    List<SysPermission> permissions;
}
