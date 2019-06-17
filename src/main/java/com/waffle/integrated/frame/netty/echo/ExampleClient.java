package com.waffle.integrated.frame.netty.echo;


import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

/**
 * @author yixiaoshuang
 * @date 2019-06-06 22:19
 */
@Slf4j
public class ExampleClient extends WebSocketClient {

    private static final String ADDRESS = "ws://127.0.0.1:8007/ws";

    public ExampleClient(URI uri) {
        super(uri);
    }


    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.warn("open");
    }

    @Override
    public void onMessage(String message) {
        log.warn("onmessage:{}", message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.warn("onclose,code;{},reason:{}", code, reason);
    }

    @Override
    public void onError(Exception ex) {
        log.error("ex,{}", ex);
    }

    public static void main(String[] args) {
        try {
            ExampleClient client = new ExampleClient(new URI(ADDRESS));
            client.setConnectionLostTimeout(20);
            client.connectBlocking();
            client.send("测试消息d");
        } catch (Exception e) {
            log.error("ex:{}", e);
        }

    }
}
