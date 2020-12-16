package com.test.j2se.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.针对V1版本的优化
 * 1.1 将线程操作和资源对象隔离，保证操作共享变量的安全
 *
 * @author : yixiaoshuang
 * @date : 2018/9/30 23:04
 * @Description:
 */
@Slf4j
public class ReentrantLockTest2 {


    private static Integer i = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public void add() {
        i++;
    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        ReentrantLockTest2 target = new ReentrantLockTest2();

        new Thread(() -> {
            lock.lock();

            try {
                System.out.println("111");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {

                }
//                for (int j = 0; j < 1000; j++) {
//                    target.add();
//                }
            } finally {
                lock.unlock();
//                countDownLatch.countDown();
            }
        }, "a").start();


        new Thread(() -> {
            lock.lock();

            try {
                for (int j = 0; j < 1000; j++) {
                    target.add();
                }
            } finally {
                lock.unlock();
                countDownLatch.countDown();
            }
        }, "b").start();
//
//        countDownLatch.await();

        System.out.println("num=" + i);
    }
}
