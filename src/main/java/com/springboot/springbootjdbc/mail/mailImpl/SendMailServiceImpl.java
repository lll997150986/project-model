package com.springboot.springbootjdbc.mail.mailImpl;

import com.springboot.springbootjdbc.mail.SendMailService;
import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-24 13:54
 **/
@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Value("${spring.mail.username}")
    private String from;

    public static final Logger logger = LogManager.getLogger(SendMailServiceImpl.class);

    @Override
    public boolean sendJunkMail(List<User> users) {
        try {
            if (users == null || users.size() <= 0){
                return  Boolean.FALSE;
            }
            for (User user : users){
                MimeMessage mimeMessage = this.mailSender.createMimeMessage();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
//                发送方
                message.setFrom(from);
                message.setSubject("一刀999  广告测试");
                message.setTo("lll997150986@163.com");
                message.setText(user.toString()+"哈哈哈哈哈哈");
                this.mailSender.send(mimeMessage);
            }
        }catch (Exception e){
            logger.error("sendJuncMail err and users=%s",users,e);
            return false;
        }
        return true;
    }
}
