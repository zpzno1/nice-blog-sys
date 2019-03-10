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
package cn.kiwipeach.blog.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 返回数据:
 * 0  成功
 * >0 业务异常
 * <0 系统错误
 *
 * @author kiwipeach [1099501218@qq.com]
 * @create 2018/07/11
 */
public class AjaxResponse<T> {
    //region 分页默认值
    private static final String DEFAULT_RESPONSE_SUCCESS_CODE = "0";
    private static final String DEFAULT_RESPONSE_MSG = "请求成功";
    //endregion
    /**
     * 返回状态码
     */
    private String code = DEFAULT_RESPONSE_SUCCESS_CODE;
    /**
     * 返回失败信息
     */
    private String msg = DEFAULT_RESPONSE_MSG;
    /**
     * 返回的目标数据
     */
    private T data;
    /**
     * 数据访问路径
     */
    //private String path;
    /**
     * 返回系统时间
     */
    private String time;

    public AjaxResponse() {
        time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public AjaxResponse(T data) {
        this();
        this.data = data;
    }

    public AjaxResponse(String code, String msg) {
        this();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 返回成功数据
     *
     * @param data 报文数据
     * @param <T>  报文类型
     * @return 统一返回报文数据
     */
    public static <T> AjaxResponse<T> success(T data) {
        return new AjaxResponse<T>(data);
    }

    /**
     * 返回失败数据
     *
     * @param code 失败状态码
     * @param msg  失败报文
     * @param <T>  报文类型
     * @return 统一返回把报文数据
     */
    public static <T> AjaxResponse<T> fail(String code, String msg) {
        AjaxResponse<T> ajaxResponse = new AjaxResponse<T>(code, msg);
        ajaxResponse.setCode(code);
        return ajaxResponse;
    }

}
