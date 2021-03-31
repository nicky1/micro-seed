package com.waffle.integrated.frame.vertx;

import io.vertx.core.AbstractVerticle;

/**
 * Created by yixiaoshuang on 2018/5/21.
 */
public class MyFirstVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(
                req -> {
                    req.response().putHeader("contenty-type", "text/html")
                            .end("hello world22233");

                }
        ).listen(8080);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

