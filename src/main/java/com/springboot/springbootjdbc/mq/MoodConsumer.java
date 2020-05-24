package com.springboot.springbootjdbc.mq;

import com.springboot.springbootjdbc.domain.Mood;
import com.springboot.springbootjdbc.service.MoodService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 15:22
 **/
@Service
public class MoodConsumer {
    @JmsListener(destination = "mood.queue")
    public void receiveQueue(String text){
        System.out.println("用户发表说说【"+text+"】成功");
    }

    @Resource
    private MoodService moodService;

    @JmsListener(destination = "mood.queue.async.save")
    public void receiveQueue(Mood mood){
        moodService.save(mood);
    }

}
