package com.waffle.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.waffle.bean.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yixiaoshuang
 */
@RestController
@RequestMapping(value = "/api/mock")
@Slf4j
public class MockController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,Serializable> redisTemplate;

    private LoadingCache<String,String> cache = CacheBuilder.newBuilder()
            .maximumSize(30)
            .expireAfterWrite(100, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load (String key) throws Exception {
                    return key+"-load-test";
                }
            });



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

        String k1 ="list1";

        List<TestBean> list = new ArrayList<>();
        list.add(bean);
        bean = new TestBean("张三2",233,new Date());
        list.add(bean);
        ListOperations operations = redisTemplate.opsForList();
        operations.leftPush(k1,list);

        List tmp = operations.range(k1,0,0);

    }

    @GetMapping("/guava/cache/test")
    public ResponseEntity testGuavaCache(@RequestParam(value = "key",required = false) String key){
        String value = StringUtils.EMPTY;
        try {
            value = cache.get(key);
            log.info("value:{}",value);
        } catch (ExecutionException e) {

        }
        log.info(cache.asMap().toString());
        return ResponseEntity.ok(value);
    }

}
