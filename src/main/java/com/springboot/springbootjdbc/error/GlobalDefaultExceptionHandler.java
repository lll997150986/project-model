package com.springboot.springbootjdbc.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: springboot-jdbc
 * @description:统一异常处理类
 * @author: libin
 * @create: 2020-05-24 16:55
 **/
//统一的异常处理类
@ControllerAdvice(basePackages = {"com.springboot.springbootjdbc"})
public class GlobalDefaultExceptionHandler {
//    捕获的异常类
    @ExceptionHandler({CommonException.class})
//    如果返回的是json对象就应该添加注解
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, Exception e){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(e.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }
}
