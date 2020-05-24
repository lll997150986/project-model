package com.springboot.springbootjdbc.serviceImpl;

import com.springboot.springbootjdbc.dao.UserDao;
import com.springboot.springbootjdbc.domain.User;
import com.springboot.springbootjdbc.error.CommonException;
import com.springboot.springbootjdbc.repository.UserRepository;
import com.springboot.springbootjdbc.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @program: springboot-jdbc
 * @description:
 * @author: libin
 * @create: 2020-05-22 18:48
 **/
@Service
public class UserServiceImpl implements UserService {


    private static final String ALL_USER="ALL_USER_LIST";

//    @Resource(name = "userRepository")
    @Autowired
    @Qualifier("userRepository")
    UserRepository userRepository;
    @Autowired
    RedisTemplate redisTemplate;

    Logger logger = LogManager.getLogger(this.getClass());

    @Resource
    UserDao userDao;

    @Transactional
    @Override
    public User save(User user) {
     return userRepository.save(user);
    }

    @Override
    public User findById(String id) {
//        redis命中
        List<User> users = redisTemplate.opsForList().range(ALL_USER, 0, -1);
        if (users != null &&users.size() > 0){
            for (User user : users){
                if (user.getId().equals(id)){
                    return user;
                }
            }
        }
//        redis未命中
        User result =  userRepository.findById(id).get();
        if (result != null){
//            查询结果加入redis
            redisTemplate.opsForList().leftPush(ALL_USER,result);
        }
        return result;
    }

    @Transactional
    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
        //        redis命中，删除缓存业务逻辑
            logger.debug("缓存命中数据已删除");
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByNameLike(String name) {
        return userRepository.findByNameLike(name);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Retryable(value = {CommonException.class},maxAttempts = 5,
    backoff = @Backoff(delay = 5000,multiplier = 2))
    @Override
    public User findByName(String name) {
//        省略redis
//        return userDao.findByName(name);
        System.out.println("findByName方法失效了！");
        throw new CommonException("findByName方法失效了！");
    }

    @Async
    @Override
    public Future<List<User>> findAsyncAll() {
        List<User> list = userRepository.findAll();
        return new AsyncResult<List<User>>(list);
    }



}
