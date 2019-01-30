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
package cn.kiwipeach.blog.enums;

import org.springframework.util.StringUtils;

/**
 * <code,value> 类型的枚举，用来存放系统开发时候出现的编码
 *
 * @author kiwipeach
 * @create 2019-01-30
 */
public enum CodeValueEnum {

    GIRL("001", "girl"),
    BOY("002", "boy"),
    ;

    private String code;
    private String message;

    CodeValueEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 由状态码获取枚举信息
     *
     * @param code 状态码
     * @return 枚举信息
     */
    public static CodeValueEnum stateOf(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("response method stateOf's args can not be empty");
        }
        for (CodeValueEnum rce : values()) {
            if (rce.getCode().equals(code)) {
                return rce;
            }
        }
        return null;
    }

}
