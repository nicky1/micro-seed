package com.test.j2se.thread;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**
 * 测试jvm堆内存溢出后,其它线程是否可继续工作
 * 测试发现:其它线程还可以继续工作。
 *
 * @author yixiaoshuang
 * @date 2020/7/31 18:04
 */
@Slf4j
public class JvmThread {

    public static void main(String[] args) {
        new Thread(() -> {
            List<byte[]> list = Lists.newArrayList();
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");

                byte[] b = new byte[1024 * 1024];
                list.add(b);
            }
        }).start();

        // 第二个线程
        new Thread(() -> {
            while (true) {
                System.out.println(new Date().toString() + Thread.currentThread() + "==");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
