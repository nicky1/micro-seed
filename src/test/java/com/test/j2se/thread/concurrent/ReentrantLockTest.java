package com.test.j2se.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/30 23:04
 * @Description:
 */
@Slf4j
public class ReentrantLockTest extends Thread{

    private ReentrantLock lock = new ReentrantLock();
    private static  Integer i = 0;

    public ReentrantLockTest(String name){
        super.setName(name);
    }

    @Override
    public void run() {
        for (int j=0;j<10;j++){
            try {
                lock.lock();
            }catch (Exception e){

            }finally {
                lock.unlock();
            }

            log.info("thread name:{},{}",this.getName(),i);
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest t1 = new ReentrantLockTest("t1");
        ReentrantLockTest t2 = new ReentrantLockTest("t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        log.info(i+"");
    }
}
