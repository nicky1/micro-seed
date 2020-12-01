package com.waffle.common.config;

import com.waffle.integrated.frame.rabbitmq.QueueNames;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

/**
 * rabbitMq configuration
 *
 * @author yixiaoshuang
 * @date 2020/5/23 21:29
 */
//@Configuration
public class RabbitConfig {

    @Bean
    public Queue demoQueue() {
        return new Queue(QueueNames.DEMO_QUEUE);
    }

    @Bean
    public Queue longAckQueue() {
        return new Queue(QueueNames.longAckQueue);
    }
}
