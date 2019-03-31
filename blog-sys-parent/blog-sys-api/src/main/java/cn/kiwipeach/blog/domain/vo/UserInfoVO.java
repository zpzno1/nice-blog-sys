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

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 账号个人信息
 *
 * @author kiwipeach
 * @create 2019-01-24
 */
@Getter
@Setter
public class UserInfoVO implements Serializable {
    /**
     * 登录账号(三方登陆账号)
     */
    private String userName;
    /**
     * 用户昵称（默认取三方平台昵称，或者登陆名称,本地系统则使用博客用户码）
     */
    private String nickName;

    /**
     * 用户头像 (默认三方平台头像，可后期自行更换头像,本地系统使用默认头像)
     */
    private String headUrl;

    /**
     * 对其他公开id
     */
    private String openId;


    /**
     * 所属平台
     */
    private String platform;


    public UserInfoVO() {
    }

    public UserInfoVO(String userName, String nickName, String headUrl, String openId, String platform) {
        this.userName = userName;
        this.nickName = nickName;
        this.headUrl = headUrl;
        this.openId = openId;
        this.platform = platform;
    }
}
