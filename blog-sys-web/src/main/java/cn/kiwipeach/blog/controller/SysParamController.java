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
package cn.kiwipeach.blog.controller;


import cn.kiwipeach.blog.base.AjaxResponse;
import cn.kiwipeach.blog.domain.SysParam;
import cn.kiwipeach.blog.domain.vo.FriendLinkVO;
import cn.kiwipeach.blog.service.ISysParamService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统参数 前端控制器
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018-08-05
 */
@Controller
@RequestMapping("/sysParam")
public class SysParamController {

    @Autowired
    private ISysParamService iSysParamService;

    /**
     * 友链参数获取
     * @return 返回友链列表信息
     */
    @GetMapping("all")
    @ResponseBody
    public AjaxResponse<List<SysParam>> allSysParam() {
        return AjaxResponse.success(iSysParamService.list(new QueryWrapper<SysParam>()));
    }


    /**
     * 友链参数获取
     * @return 返回友链列表信息
     */
    @GetMapping("friendList")
    @ResponseBody
    public AjaxResponse<List<FriendLinkVO>> friendListLink() {
        return AjaxResponse.success(iSysParamService.queryFriendListLink());
    }

}
