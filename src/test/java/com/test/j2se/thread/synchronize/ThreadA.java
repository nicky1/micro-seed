package com.test.j2se.thread.synchronize;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/28 08:56
 * @Description:
 */
public class ThreadA implements Runnable {

    private ObjectService service;

    public ThreadA(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        try {
            service.synMethodA();
        } catch (InterruptedException e) {

        }
    }
}
