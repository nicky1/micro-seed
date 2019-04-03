package com.waffle.frame.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/11/13 21:15
 * @Description:
 */
public class VertxEventBus {
    public static void main(String[] args) {
        EventBus bus = Vertx.vertx().eventBus();

    }
}
