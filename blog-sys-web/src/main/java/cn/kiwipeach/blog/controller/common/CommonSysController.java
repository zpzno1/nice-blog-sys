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
package cn.kiwipeach.blog.controller.common;

import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.vo.UserInfoVO;
import cn.kiwipeach.blog.service.IBlogCommService;
import org.apache.shiro.SecurityUtils;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 博客系统基础控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/11
 */
@Controller
public class CommonSysController {

    /**
     * 获取当前登陆用户信息
     *
     * @return 返回当前登陆用户信息
     */
    @GetMapping("user")
    @ResponseBody
    public AjaxResponse<UserInfoVO> queryUserInfo() {
        AccessToken currentUser = UserUtil.getCurrentUser();
        if (currentUser == null) {
            return AjaxResponse.success(null);
        } else {
            //FIXME openid建议不要跟id一致，后期在做处理了。
            UserInfoVO userInfoVO = new UserInfoVO(currentUser.getUserName(), currentUser.getNickName(), currentUser.getHeadUrl(), currentUser.getId(), currentUser.getPlatform());
            return AjaxResponse.success(userInfoVO);
        }
    }


}
