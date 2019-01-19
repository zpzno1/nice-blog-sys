package org.kiwipeach.blog.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 500异常,系统异常和业务异常;400异常有系统自动转发到/error请求
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 统一异常处理只对500错误进行处理，对于4xx错误系统会自动处理
     */
    @ExceptionHandler(Exception.class)
    public String toDefaultErrorController(Exception e, HttpServletRequest request) {
        logger.error("服务器内部错误:", e);
        Map<String, String> paramMap = new HashMap<>();
        request.setAttribute("javax.servlet.error.status_code", 500);
        //paramMap.put("author", "kiwipeach");
        //paramMap.put("email", "10999051218@qq.com");
        //request.setAttribute("paramMap", paramMap);
        return "forward:/error";
    }


}
