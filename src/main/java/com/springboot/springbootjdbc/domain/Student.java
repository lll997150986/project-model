package com.springboot.springbootjdbc.domain;

import lombok.Data;

import javax.persistence.Id;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 19:08
 **/
@Data
public class Student  {
    @Id
    private String id;
    private String name;

}
