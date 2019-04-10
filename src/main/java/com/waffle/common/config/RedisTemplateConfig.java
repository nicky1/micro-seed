package com.waffle.common.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/7/3 17:16
 * @Description: redis client lettuce
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String,Serializable> redisTemplate(){

        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory());
        return template;
    }

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        LettuceConnectionFactory factory = new LettuceConnectionFactory("127.0.0.1",6379);
//        factory.setPassword(password);
//        factory.setTimeout(timeout);
        return factory;
    }
}
