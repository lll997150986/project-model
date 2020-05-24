package com.springboot.springbootjdbc.config;

import com.springboot.springbootjdbc.mail.SendMailService;
import com.springboot.springbootjdbc.mail.mailImpl.SendMailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 14:16
 **/
@Configuration
public class CommonConfig {

    @Bean
    public SendMailService sendMailService(){
        return  new SendMailServiceImpl();
    }
}
