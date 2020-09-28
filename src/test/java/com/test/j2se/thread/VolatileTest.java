package com.test.j2se.thread;

/**
 * 测试volatile关键字
 * 内存可见性测试
 * ChangeMaker每隔0.5秒增加COUNTER的值,到5结束。
 * ChangeListener忙等待监听COUNTER值的变化
 *
 * @author yixiaoshuang
 * @date 2020/9/28 17:33
 */
public class VolatileTest {

    // 这里去掉volatile,ChangeListener线程会陷入死循环。因为读取不到最新的COUNTER
    private static volatile int COUNTER = 0;

    public static void main(String[] args) {

        Thread t1 = new Thread(new ChangeListener());
        Thread t2 = new Thread(new ChangeMaker());
        t1.start();
        t2.start();
    }


    static class ChangeListener extends Thread {
        int threadValue = COUNTER;

        @Override
        public void run() {
            while (threadValue < 5) {
                if (threadValue != COUNTER) {
                    System.out.println("Got Change for COUNTER : " + COUNTER + "");
                    threadValue = COUNTER;
                }
                // 这里加上5毫秒的sleep,COUNTER已经有机会刷新到CPU CACHE中。
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {

                }
            }
        }
    }

    static class ChangeMaker extends Thread {

        @Override
        public void run() {
            int threadValue = COUNTER;
            while (COUNTER < 5) {
                System.out.println("Incrementing COUNTER to : " + (threadValue + 1) + "");
                COUNTER = ++threadValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                }
            }
        }
    }

}
