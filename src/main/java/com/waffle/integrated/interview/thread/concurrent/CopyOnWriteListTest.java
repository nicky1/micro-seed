package com.waffle.integrated.interview.thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试并发安全的ArrayList: CopyOnWriteArrayList
 *
 * @author yixiaoshuang
 * @date 2020/12/2 08:46
 */
public class CopyOnWriteListTest {

    public static void main(String[] args) {
        List list = new ArrayList();

//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    list.add(UUID.randomUUID().toString());
//                }
//            },String.valueOf(i)).start();
//        }
    }

}
