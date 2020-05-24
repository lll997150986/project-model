package com.springboot.springbootjdbc.mail;

import com.springboot.springbootjdbc.domain.User;

import java.util.List;

public interface SendMailService {
    boolean sendJunkMail(List<User> user);
}
