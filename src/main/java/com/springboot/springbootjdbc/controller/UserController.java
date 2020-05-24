package com.springboot.springbootjdbc.controller;

import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-22 19:36
 **/
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public List<User> show(){
        List<User> all = userService.findAll();
        return all;
    }
    @RequestMapping("/findByNameRetry")
    public String findByNameRetry(String name){
        User user = userService.findByName(name);
        return "success";
    }
}
