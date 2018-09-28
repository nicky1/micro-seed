package com.test.j2se.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/28 08:53
 * @Description:
 */
@Slf4j
public class ObjectService {

    public void synMethodA() throws InterruptedException {
        synchronized (ObjectService.class){
            log.info("A beging time :{}",System.currentTimeMillis());
            Thread.sleep(2000);
            log.info("A end time :{}",System.currentTimeMillis());
        }
    }

    public void synMethodB() throws InterruptedException {
        synchronized (ObjectService.class){
            log.info("B beging time :{}",System.currentTimeMillis());
            log.info("B end time :{}",System.currentTimeMillis());
        }
    }
}
