package com.waffle.controller;

import com.waffle.integrated.frame.netty.echo.ExampleClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

/**
 * @author yixiaoshuang
 * @date 2019-06-07 01:05
 */
@RestController
@RequestMapping(value = "/api/v1/netty")
public class TestNettyApi {
    private static final String ADDRESS = "ws://127.0.0.1:8007";

    /**
     * 测试使用websocket client发送socket消息
     *
     * @param
     */
    @PostMapping(value = "/test/send")
    public ResponseEntity test1(@RequestParam("msg") String msg) throws Exception {
        ExampleClient client = new ExampleClient(new URI(ADDRESS));
        client.connectBlocking();
        client.send("3333");
        return ResponseEntity.ok().build();
    }

}
