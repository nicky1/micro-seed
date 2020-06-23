package com.waffle.integrated.frame.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2020/5/31 21:13
 */
@Component
public class Sender {

    @Resource
    private AmqpTemplate rabbitTemplate;

    public void send(String key, String value) {
        rabbitTemplate.convertAndSend(key, value);
    }
}
