package com.springboot.springbootjdbc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-22 18:42
 **/
@Data
@Entity
@Table(name = "tb_user")
public class User  implements Serializable {
    @Id
    private String id;
    private String name;
    private String password;
}
