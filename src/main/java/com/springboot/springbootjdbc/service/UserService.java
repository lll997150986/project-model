package com.springboot.springbootjdbc.service;

import com.springboot.springbootjdbc.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.concurrent.Future;

public interface UserService {
    User save(User user);
    User findById(String id);
    void delete(String id);
    List<User> findAll();
    List<User> findByNameLike(String name);
//    分页
    Page<User> findAll(Pageable pageable);
    User findByName(String name);
//异步查询
    Future<List<User>> findAsyncAll();


}

