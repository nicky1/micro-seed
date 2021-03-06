package com.test.j2se.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock测试V1版本
 *
 * @author : yixiaoshuang
 * @date : 2018/9/30 23:04
 * @Description:
 */
@Slf4j
public class ReentrantLockTest extends Thread {

    private ReentrantLock lock = new ReentrantLock();
    private static Integer i = 0;

    public ReentrantLockTest(String name) {
        super.setName(name);
    }

    /**
     * 这里即使使用了lock，也不能版本保证线程安全。
     * 因为lock对象是属于每个线程对象的。
     * 即线程操作资源，要把资源和线程操作 解耦。
     * 1.可以使用volatile关键字修饰保证共享变量的内存可见性
     */
    @Override
    public void run() {
        lock.lock();
        try {
            for (int j = 0; j < 1000; j++) {
//                log.info("thread name:{},{}", this.getName(), i);
                i++;

            }

        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest t1 = new ReentrantLockTest("t1");
        ReentrantLockTest t2 = new ReentrantLockTest("t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("i=" + i);
    }
}
