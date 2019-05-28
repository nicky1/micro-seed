package com.test.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * guava cache
 * https://www.cnblogs.com/dennyzhangdd/p/8981982.html
 * @author yixiaoshuang
 * @date 2019-05-27 16:59
 */
@Slf4j
public class TestGuavaCache {

    public static void main (String[] args) throws ExecutionException {
        //loading cache支持在get方法时，如果缓存不存在对应的key记录，则cacheloader中的load()会被自动调用加载数据到缓存中
        LoadingCache<String,String> cache = CacheBuilder.newBuilder()
                    .maximumSize(30)
                    .expireAfterWrite(1000, TimeUnit.SECONDS)
                    .build(new CacheLoader<String, String>() {
                        @Override
                        public String load (String key) throws Exception {
                            return key+"-auto-load";
                        }
                    });
//        String key = "world2";
//
//        cache.put("world","hello guava cache");
//        cache.put("a","1");
//        cache.put("b","2");
//        cache.put("c","3");
//
//        String v1 = cache.get(key);
//        String v2 = cache.get("a");

//        log.info("v1:{},v2:{}",v1,v2);
        log.info(cache.asMap().toString());

    }

}
