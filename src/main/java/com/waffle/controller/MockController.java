package com.waffle.controller;

import com.alibaba.fastjson.JSON;
import com.waffle.bean.TestBean;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by yixiaoshuang on 2018/6/21.
 */
@RestController
@RequestMapping(value = "/api/mock")
@Slf4j
public class MockController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,Serializable> redisTemplate;

    @RequestMapping(value = "/ribbon/test" , method = RequestMethod.GET)
    public String testRibbon(HttpServletRequest request, @RequestParam(value = "name",required = false) String name){
        int port = request.getServerPort();
        return name+";"+port;
    }

    @RequestMapping(value = "/redis" , method = RequestMethod.GET)
    public void testRedis(){
//        RedisClient client= RedisClient.create("redis://localhost:7617");
//        StatefulRedisConnection connection = client.connect();
//        RedisCommands command = connection.sync();
//        String s = (String) command.get("test");
//        log.info(s);
//        TestBean bean3 = new TestBean("张三33",23,null);
//
//        command.set("object-bean-set",bean3);
//
//        TestBean testBean2 = (TestBean) command.get("object-bean-set");
//        log.info(JSON.toJSONString(testBean2));



//        command.set("test","22222333");
//        String s2 = (String) command.get("test");
//        log.info(s2);


        String s3 = stringRedisTemplate.opsForValue().get("test");
        log.info(s3);

//        redisTemplate.opsForValue().increment()

        stringRedisTemplate.opsForValue().set("test","444");
        String s4 = stringRedisTemplate.opsForValue().get("test");
        log.info(s4);

        TestBean bean = new TestBean("张三",23,new Date());
        redisTemplate.opsForValue().set("user",bean);

        TestBean testBean = (TestBean) redisTemplate.opsForValue().get("user");
        log.info(testBean.toString());
    }

}
