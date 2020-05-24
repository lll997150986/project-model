package com.springboot.springbootjdbc;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.springboot.springbootjdbc.domain.Mood;
import com.springboot.springbootjdbc.domain.Student;
import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.mq.MoodProducer;
import com.springboot.springbootjdbc.service.MoodService;
import com.springboot.springbootjdbc.service.StudentService;
import com.springboot.springbootjdbc.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import javax.persistence.Table;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootJdbcApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private MoodService moodService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private MoodProducer  moodProducer;
    @Autowired
    private StudentService studentService;

   private static final   Logger logger = LogManager.getLogger(SpringbootJdbcApplicationTests.class);

    @Test
    void contextLoads() throws SQLException {
        User user = new User();
        user.setId("2");

        user.setName("山1231");
        user.setPassword("213123");
        userService.save(user);
        System.out.println(userService.findAll());
    }
    @Test
    void queryLike(){
        String name = "山%";

        User user = userService.findByNameLike(name).get(0);
        System.out.println(user);
        Assert.assertNotNull(user);
    }
    @Test
    void testRedis(){
        redisTemplate.opsForValue().set("name","zhangsan");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void testFindById(){
        Long redisUserSize = 0L;
        User user = userService.findById("1");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("当前缓存中的用户数量为：" + redisUserSize);
        System.out.println(user);

        User user2 = userService.findById("3");
        redisUserSize = redisTemplate.opsForList().size("ALL_USER_LIST");
        System.out.println("缓存未命中,当前缓存中的用户数量为：" + redisUserSize);
        System.out.println(user2);
    }

    @Test
    void testDelete(){
        userService.delete("3");
    }

    @Test
    void testMybatisFindUserByName(){
        String name = "占山";
        User user = userService.findByName(name);
        Assert.assertNotNull(user);
        logger.info("->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>user:"+user);
    }

    @Test
    void testSaveMood(){
        Mood mood = new Mood();
        mood.setId("1");
        mood.setContent("第一条说说！！");
        mood.setPraiseNum(0);
        mood.setUserId("1");
        mood.setPublishTime(new Date());
        moodService.save(mood);
    }
    @Test
    void testActiveMQ(){
        Destination destination = new ActiveMQQueue("mood.queue");
        moodProducer.sendMessage(destination,"helllo,mq!");
    }

    @Test
    void testActiveMQAsyncSave(){
        Mood mood = new Mood();
        mood.setId("2");
        mood.setUserId("2");
        mood.setPublishTime(new Date());
        mood.setPraiseNum(0);
        mood.setContent("第二条说说！");
        String s = moodService.asyncSave(mood);
        System.out.println("异步发表说说："+ s);
    }

    @Test
    void testSaveByMongoDB(){
//        主要就是在yml中配置和repository改变一下，其他没有变动
        Student student = new Student();
        student.setId("1");
        student.setName("mongodb test");
        studentService.save(student);

    }

}
