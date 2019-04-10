package com.waffle.integrated.frame.rxjava;

import io.reactivex.Flowable;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/8/24 17:22
 * @Description:
 */
public class HelloWorld {
    public static void main(String[] args) {
        Flowable.just("hello world").subscribe(System.out::println);
    }
}
