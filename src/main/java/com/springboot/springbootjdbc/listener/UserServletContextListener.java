package com.springboot.springbootjdbc.listener;

import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionListener;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-22 20:57
 **/

/**
 * ServletContextListener监听application创建和销毁，HttpSessionListener,ServletRequestListener监听对象域中属性增加和删除
 *  ServletRequestAttributeListener，HttpSessionAttributeListener，ServletContextAttributeListener
 *  监听绑定到session某个对象的状态
 */
@WebListener
public class UserServletContextListener implements ServletContextListener {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;
    private static final String ALL_USER = "ALL_USER_LIST";
    private static AtomicInteger visits = new AtomicInteger(0);
    Logger logger = LogManager.getLogger(this.getClass());
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        logger.debug("当前在线人数："+visits.incrementAndGet());
//        System.out.println("当前在线人数："+visits.incrementAndGet());
        List<User> users = userService.findAll();
//        清除缓存中的数据
        redisTemplate.delete(ALL_USER);
        redisTemplate.opsForList().leftPushAll(ALL_USER,users);

//        在实际项目中应该注释掉,查询所有用户信息
        List list = redisTemplate.opsForList().range(ALL_USER, 0, -1);

        logger.debug("当前已缓存"+list.size()+"人");
//        System.out.println("当前已缓存"+list.size()+"人");
        logger.debug("servletContext上下文初始化");
//        System.out.println("servletContext上下文初始化");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.debug("当前在线人数："+visits.decrementAndGet());
//        System.out.println("当前在线人数："+visits.decrementAndGet());
    }
}
