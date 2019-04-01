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
package cn.kiwipeach.blog.utils;

import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.kiwipeach.blog.shiro.token.AccessToken;
import org.kiwipeach.blog.utils.UserUtil;

/**
 * 博客详情页面的工具类
 *
 * @author kiwipeach
 * @create 2019-03-23
 */
public abstract class BlogDetailPageUtil {

    /**
     * 获取博客评论按钮数据
     *
     * @param blog 博客对象
     * @return 返回博客关键数据
     */
    public static String getBlogMainData(BlogInfoVO blog) {
        JSONObject keyMap = new JSONObject();
        keyMap.put("parentId", blog.getId());
        keyMap.put("passiveUserId", blog.getUserId());
        keyMap.put("commentCount", blog.getCommentCount());
        keyMap.put("starCount", blog.getStarCount());
        AccessToken currentUser = UserUtil.getCurrentUser();
        if (currentUser != null) {
            keyMap.put("activeUserId", currentUser.getId());
        }
        return JSON.toJSONString(keyMap);
    }


}
