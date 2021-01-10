package com.waffle.service;

import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

/**
 * @author yixiaoshuang
 * @date 2020/12/26 18:01
 */
@Service
public class SpringIocServiceTest1 {

    public void test1(){
        System.out.println("test1");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
