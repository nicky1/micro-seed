package com.waffle.interview.collection;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/8/6 23:17
 * @Description:
 */
@Slf4j
public class TestArrayList{

  public static void main(String[] args) {

      List list = Lists.newArrayList();

      String s =new String("a");

      s = new String("d");

      LinkedList linkedList = Lists.newLinkedList();
      linkedList.add(1);
      linkedList.add(3);
      linkedList.add(0);


      linkedList.stream().forEach(v ->{
          log.info("data : {}",v);
      });

      ConcurrentMap map = new ConcurrentHashMap();



  }

}
