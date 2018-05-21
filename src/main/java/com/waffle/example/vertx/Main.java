package com.waffle.example.vertx;

import io.vertx.core.Vertx;

/**
 * vert.x first demo
 * Created by yixiaoshuang on 2018/5/21.
 */
public class Main {
    public static void main(String[] args) {
        Vertx v = Vertx.vertx();
        v.deployVerticle(MyFirstVerticle.class.getName());
    }
}
