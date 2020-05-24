package com.springboot.springbootjdbc.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 15:06
 **/
@Data
@Entity
@Table(name = "tb_mood")
public class Mood implements Serializable {

    @Id
    private String id;
    private String content;
    private String userId;
    private Integer praiseNum;
    private Date publishTime;

}
