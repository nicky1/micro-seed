package com.test.vertx;

import com.waffle.example.vertx.MyFirstVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/11/14 10:34
 * @Description:
 */
@Slf4j
@RunWith(VertxUnitRunner.class)
public class VertxTest {

    private Vertx vertx;


    @Before
    public void setup(TestContext context){
        vertx = Vertx.vertx();
        vertx.deployVerticle(MyFirstVerticle.class.getName(),context.asyncAssertSuccess());
    }

    @Test
    public void testApp(TestContext context){
        final Async async = context.async();
        vertx.createHttpClient().getNow(8080,"localhost","/",response->{
           response.handler(body -> {
               log.info("222");
               context.assertTrue(body.toString().contains("hello"));
               async.complete();
           }) ;
        });
    }

    @Test
    public void testPeriod(){
        vertx.setPeriodic(1000,id->{
           log.info("timer fired,id:{}",id);
        });
    }

    @Test
    public void testBlocking(){
        vertx.executeBlocking(future -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            future.complete();
        },res->{
            System.out.println("2222");
            log.info("done");
        });

    }
}
