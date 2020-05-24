package com.springboot.springbootjdbc.error;

import lombok.Data;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 16:53
 **/
@Data
public class ErrorInfo<T> {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 100;

//    错误信息
    private Integer code;
//    错误码
    private String message;
    private String url;
    private T data;
}
