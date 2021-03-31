package com.waffle.integrated.frame.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/11/21 22:43
 * @Description: web http route
 */
@Slf4j
public class MyHttpServerRoute {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer server = vertx.createHttpServer();

        Router router = Router.router(vertx);

        router.route("/").handler(req -> {
            req.response().end("all");
        });

        //get
        router.route("/index").handler(req -> {
            req.response().end("index");
        });

        //post
        router.post("/do/post").handler(req -> {
            req.response().end("do post23");
        });

        //get param
        router.route(HttpMethod.GET, "/get").handler(req -> {
            String a = req.request().getParam("a");
            log.info(a);
            req.response().end("get get");
        });


        server.requestHandler(router::accept);
        server.listen(9999);
    }
}
