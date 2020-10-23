package com.test.j2se.collection.map;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.*;

@Slf4j
public class ConcurrentHashMapTest extends TestCase {

    

    public static void main(String[] args) {
        new ConcurrentHashMapTest().test1();
    }
    
    public void test1(){
        ConcurrentHashMap<String, String> testMap=new ConcurrentHashMap<String, String>();
//        testMap.put("111", "aaaa");
//        testMap.put("222", "bbbb");
//        testMap.put("333", "cccc");
//        
//        log.info(testMap.size());
//        testMap.get("111");
        //ConcurrentHashMap<K, V>
        String s = testMap.put("1","22");
        s = testMap.put("1","22333");
        log.info(s);

        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("test").build();

        ExecutorService service = new ThreadPoolExecutor(5,10,1000, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                factory);

        
    }

}
