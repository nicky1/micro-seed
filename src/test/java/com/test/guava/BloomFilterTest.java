package com.test.guava;


import com.google.common.collect.Maps;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * BloomFilter，解决缓存穿透，使用guava的工具类
 * @author yixiaoshuang
 * @date 2019-05-23 15:13
 */
@Slf4j
public class BloomFilterTest {
    private static int size = 9000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(),size);

    private static Map<Integer,Integer> map = Maps.newHashMap();
    public static void main (String[] args) {
        long startTime = System.nanoTime(); // 获取开始时间

        for (int i=0;i<size;i++){
            bloomFilter.put(i);
//            map.put(i,i);
        }
        Integer target = 9999999;
        List<Integer> list = new ArrayList<Integer>(1000);
        // 故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());

        long endTime = System.nanoTime();   // 获取结束时间
        log.info("程序运行时间： " + (endTime - startTime)/1000000 + "毫秒");
    }

}
