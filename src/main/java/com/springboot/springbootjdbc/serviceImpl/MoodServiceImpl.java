package com.springboot.springbootjdbc.serviceImpl;

import com.springboot.springbootjdbc.domain.Mood;
import com.springboot.springbootjdbc.mq.MoodProducer;
import com.springboot.springbootjdbc.repository.MoodRepository;
import com.springboot.springbootjdbc.service.MoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 15:10
 **/
@Service
public class MoodServiceImpl  implements MoodService {
    @Autowired
    MoodRepository moodRepository;

    //    队列
    private static Destination destination = new ActiveMQQueue("mood.queue.async.save");
    @Autowired
    MoodProducer moodProducer;

    @Override
    public Mood save(Mood mood) {
        return moodRepository.save(mood);
    }




    @Override
    public String asyncSave(Mood mood) {
        moodProducer.sendMessage(destination, mood);
        return "SUCCESS";
    }
}
