package com.springboot.springbootjdbc.repository;

import com.springboot.springbootjdbc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-22 18:42
 **/

public interface UserRepository  extends JpaRepository<User,String> {
//    自定义查询，遵守JPA规范，会被自动转换sql
    List<User> findByNameLike(String name);

}
