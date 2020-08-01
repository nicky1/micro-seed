package com.waffle.controller;

import com.google.common.collect.Lists;
import com.waffle.integrated.frame.rabbitmq.QueueNames;
import com.waffle.integrated.frame.rabbitmq.Sender;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    @GetMapping(value = "/api/test/heap")
    public ResponseEntity testHeapOutOfMemory() {
        new Thread(() -> {
            List<byte[]> list = Lists.newArrayList();
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");

                byte[] b = new byte[1024 * 1024];
                list.add(b);
            }
        }).start();

        // 第二个线程
        new Thread(() -> {
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return ResponseEntity.ok().build();
    }
}
