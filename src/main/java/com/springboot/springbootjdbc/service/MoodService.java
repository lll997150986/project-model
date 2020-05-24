package com.springboot.springbootjdbc.service;

import com.springboot.springbootjdbc.domain.Mood;

public interface MoodService {
    Mood save(Mood mood);

    //    异步消费,即发送mood，存到数据库中
    String asyncSave(Mood mood);

}
