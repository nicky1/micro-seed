package com.test.j2se.thread.synchronize;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/9/27 23:44
 * @Description: synchronized 锁定对象
 *      1.同步普通方法，锁定时当前对象
 *      2.同步静态方法，当前class 对象。
 *      3.同步 块，锁的是 ()
 */
public class Test1Syn {

    public static void main(String[] args) {
        //synchronized(this):同步代码块时，其它线程堆同一个对象(objectservice) 的其它synchronized(this)代码块的访问
        //将会阻塞，synchronized(this) 的监视器 使用的是同一个。

        ObjectService service = new ObjectService();
        ObjectService service2 = new ObjectService();

        ThreadA threadA = new ThreadA(service);
        new Thread(threadA).start();

        ThreadB threadB = new ThreadB(service);
        new Thread(threadB).start();

        //
    }
}



