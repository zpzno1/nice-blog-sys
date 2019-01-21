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
package cn.kiwipeach.blog.exception;

/**
 * 博客系统统一异常，最好需要有异常状态码查询信息：
 *  系统异常==>code<0
 *  业务异常==>code>0
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/08/06
 */
public class BlogException extends RuntimeException{
    private static final String DEFAULT_EXCEPTION_CEDE = "-1";
    private String code = DEFAULT_EXCEPTION_CEDE;
    public BlogException() {
        super();
    }

    public BlogException(String code,String message) {
        super(message);
        this.code = code;
    }

    public BlogException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlogException(Throwable cause) {
        super(cause);
    }

    protected BlogException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
