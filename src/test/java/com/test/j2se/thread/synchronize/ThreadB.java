package com.test.j2se.thread.synchronize;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/28 08:56
 * @Description:
 */
public class ThreadB implements Runnable {

    private ObjectService service;

    public ThreadB(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            service.synMethodB();
        } catch (InterruptedException e) {

        }
    }
}
