package com.waffle.integrated.interview.thread.concurrent;

import com.google.common.collect.Maps;


import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.*;

public class ConcurrentHashMapTest {



    public static void main(String[] args) {

        ExecutorService service = new ThreadPoolExecutor(1, 2, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1));
//
        HashMap<String, String> map = Maps.newHashMap();
//
        service.execute(() -> {
            map.put(UUID.randomUUID().toString().substring(0, 5), "hello");
            System.out.println(map);
        });

        service.shutdown();

    }



}
