package com.test.j2se.singleton;

import java.util.concurrent.TimeUnit;

/**
 * 单例模式类的编写
 * 1、单例类只能有一个实例。
 * 　　2、单例类必须自己自己创建自己的唯一实例。
 * 　　3、单例类必须给所有其他对象提供这一实例。
 * 要考虑多线程情况下,使用DCL()
 *
 * @author Administrator
 */
public class Singleton03 {

    private static  Singleton03 instance = null;

    //通过构造方法限定为private,避免了类在外部被实例化,这样该类的实例只能通过getInstance()获取.
    private Singleton03() {
    }

    public static Singleton03 getInstance() throws InterruptedException {
        if (instance == null) {
            synchronized (Singleton03.class) {
                if (instance == null) {
                    // 尝试模拟
                    TimeUnit.MILLISECONDS.sleep(100);
                    instance = new Singleton03();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            Thread thread = new Thread(() -> {
                Singleton03 instance = null;
                try {
                    instance = Singleton03.getInstance();
                } catch (InterruptedException e) {

                }
                System.out.println(instance);
            });

            thread.start();

        }
    }
}
