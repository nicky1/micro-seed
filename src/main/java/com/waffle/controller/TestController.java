package com.waffle.controller;

import com.waffle.integrated.frame.rabbitmq.QueueNames;
import com.waffle.integrated.frame.rabbitmq.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2020/5/31 21:12
 */
@RestController
public class TestController {

    @Resource
    private Sender sender;

    @GetMapping(value = "/api/test/mq/ack")
    public ResponseEntity mqLongAck(@RequestParam String content) {
        sender.send(QueueNames.longAckQueue, content);
        return ResponseEntity.ok().build();
    }
}
