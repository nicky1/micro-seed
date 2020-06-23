package com.waffle.integrated.frame.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yixiaoshuang
 * @date 2020/5/31 21:24
 */
@Component
@Slf4j
public class MqReceiver {

    @RabbitHandler
    @RabbitListener(queues = {QueueNames.longAckQueue})
    public void testLongTimeAck(String content) {
        log.info("content:{}", content);
        try {
        } catch (Exception e) {

        }
    }
}
