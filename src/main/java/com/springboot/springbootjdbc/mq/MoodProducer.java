package com.springboot.springbootjdbc.mq;

import com.springboot.springbootjdbc.domain.Mood;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 15:20
 **/
@Service
public class MoodProducer {
//    发消息的工具类，也可使用JmsTemplate，JmsMessagingTemplate是对JmsTemplate的封装
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message){
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, final Mood mood){
        jmsMessagingTemplate.convertAndSend(destination,mood);
    }
}
