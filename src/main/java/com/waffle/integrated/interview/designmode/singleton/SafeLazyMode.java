package com.waffle.integrated.interview.designmode.singleton;

import java.util.Objects;

/**
 * 懒汉式
 *
 * @author yixiaoshuang
 * @date 2020/12/10 08:51
 */
public class SafeLazyMode {

    private SafeLazyMode() {
        System.out.println("构造方法执行:" + Thread.currentThread().getName() + "ok");
    }

    private static SafeLazyMode INSTANCE = null;

    public static SafeLazyMode getInstance() {
        if (Objects.isNull(INSTANCE)) {
            INSTANCE = new SafeLazyMode();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        // 单线程模式下
//        LazyMode instance = LazyMode.getInstance();
//        LazyMode instance2 = LazyMode.getInstance();

//        System.out.println(instance);
//        System.out.println(instance2);

        // 多线程模式下:LazyMode可能会被初始化多次,不能达到单例的目的。
        for (int i = 0; i < 10; i++) {
            new Thread(() -> SafeLazyMode.getInstance()).start();
        }
    }
}
