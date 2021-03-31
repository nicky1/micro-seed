package com.test.j2se.thread.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/27 00:16
 * @Description: 1.需要了解内存结构，每个线程会有独立的工作内存，即栈内存，保存 主内存的副本。
 * 2.volatile能达到 内存可见性 。
 */
@Slf4j
public class Test1Volatile implements Runnable {

    public volatile boolean isRuning = true;

    public void stop() {
        this.isRuning = false;
    }

    public static void main(String[] args) {
        Test1Volatile t = new Test1Volatile();
        new Thread(t, "thread volatile").start();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String value = sc.next();
            if ("1".equals(value)) {
                t.stop();
                break;
            }
        }
        log.info("shut down");
    }

    @Override
    public void run() {
        while (isRuning) {

        }
        log.info(Thread.currentThread().getName() + "执行完毕");
    }
}
