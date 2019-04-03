package com.waffle.frame.vertx;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

/**
 * vert.x first demo
 * Created by yixiaoshuang on 2018/5/21.
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Vertx v = Vertx.vertx();
        v.deployVerticle(MyFirstVerticle.class.getName(),res->{
            log.info(res.succeeded()+"");
        });





    }
}
