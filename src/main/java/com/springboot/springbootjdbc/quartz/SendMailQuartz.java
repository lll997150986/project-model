package com.springboot.springbootjdbc.quartz;

import com.springboot.springbootjdbc.mail.SendMailService;
import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 13:32
 **/

/**
 * @Configurable作用：加上此注解的类相当于Xml配置文件，可以被springboot扫描初始化
 * @EnableScheduling开启对计划任务的支持，在执行任务的方法上加@Scheduled声明这是一个计划任务
 */
@Component
@Configurable
@EnableScheduling
public class SendMailQuartz {
    private  static final Logger logger = LogManager.getLogger(SendMailQuartz.class);

    @Autowired
    UserService userService;
    @Autowired
    SendMailService sendMailService;
    @Scheduled(cron = "*/5 * *  * * *")
    public void reportCurrent(){
        List<User> users = userService.findAll();
        if (users == null || users.size() <= 0){
            return;
        }

        sendMailService.sendJunkMail(users);
        logger.info("定时器运行了！！！");
    }

}
