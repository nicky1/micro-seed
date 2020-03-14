package com.waffle.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yixiaoshuang
 * @date 2020/3/14 10:59
 */
@RestController
@Slf4j
public class ThreadLocalController {

    private ThreadLocal<Integer> userThread = ThreadLocal.withInitial(() -> null);

    @GetMapping("/api/test/threadLocal")
    public ResponseEntity testThreadLocal(@RequestParam int userId) {

        // threadLocal存储结构:用Map存储,key-当前threadLocal实例,value-弱引用。
        // 因为web容器会线程池复用，所以使用threadLocal存放数据，要考虑篡改。需要在使用完成后,显示地去情况设置的数据。
        // 这样也能避免 内存泄露。

        String before = Thread.currentThread().getName() + ";" + userThread.get();
        log.info("before:{}", before);

        userThread.set(userId);

        String after = Thread.currentThread().getName() + ";" + userThread.get();
        log.info("after:{}", after);
        return ResponseEntity.ok().build();
    }

}
