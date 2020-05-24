package com.springboot.springbootjdbc.dao;


import com.springboot.springbootjdbc.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User findByName(@Param("name") String name);
}
