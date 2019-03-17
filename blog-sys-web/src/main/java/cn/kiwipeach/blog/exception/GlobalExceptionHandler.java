package cn.kiwipeach.blog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
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
        logger.error("######################################################################################################################################################################");
        logger.error("服务器内部错误:", e);
        LinkedList<StackTraceElement> humanExceptionStack = getHumanExceptionStack(e);
        logger.error("######################################################################################################################################################################");
        request.setAttribute("javax.servlet.error.status_code", 500);
        request.setAttribute("humanExceptionStack", humanExceptionStack);
        //出现了异常，发送份邮件给站长，让站长能够及时处理
        return "forward:/error";
    }


    private LinkedList<StackTraceElement> getHumanExceptionStack(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        LinkedList<StackTraceElement> resultStack = new LinkedList<>();
        for (StackTraceElement element : stackTrace) {
            if (element.getClassName().contains("kiwipeach")) {
                resultStack.add(element);
            }
        }
        return resultStack;
    }


}
