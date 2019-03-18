package com.test.j2se.collection.list;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-03-10 00:18
 * @Description:
 */
@Slf4j
public class TestArray {

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(4);

        for (Integer i : list){
            log.info("i:{}",i);
        }

        log.info("\n");
        list.add(2,10);

        for (Integer i : list){
            log.info("j:{}",i);
        }
    }
}
