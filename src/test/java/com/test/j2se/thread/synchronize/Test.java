package com.test.j2se.thread.synchronize;

import lombok.extern.slf4j.Slf4j;

/**
 * synchronized的学习：
 * http://www.warting.com/program/201112/39394.html
 *
 * @author Administrator
 */
@Slf4j
public class Test implements Runnable {

    String name;

    Integer a = 1;

    public static void main(String[] args) {
        Test t = new Test();
        Thread t1 = new Thread(t);
        Test test = new Test();

        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
    }

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            testSynch();
            goF1();
        } catch (InterruptedException e) {
        }
    }

    private void testSynch() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                a++;
//				Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ";" + a);
            }

        }

    }

    private void goF1() {
        log.info("go f1 ");
    }

}

