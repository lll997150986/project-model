package com.springboot.springbootjdbc.error;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 16:53
 **/
public class CommonException extends RuntimeException {
    public CommonException() {
    }

    public CommonException(String message) {
        super(message);
    }
}
