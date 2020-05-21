package com.waffle.controller.geekbang.commonmistakes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * 弱引用也能导致OOM
 *
 * @author yixiaoshuang
 * @date 2020/4/25 21:06
 */
@RestController
@Slf4j
public class WeakHashOomController {

    // 定义一个weakhashmmap
    private Map<User, UserProfile> cache = new WeakHashMap<>();
    private Map<User, WeakReference<UserProfile>> cache2 = new WeakHashMap<>();

    @GetMapping(value = "/api/gk/common/weakhashmapoom")
    public void wrong(@RequestParam long num) {
        String userName = "oom";
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                log.info("size:{}", cache.size()), 1, 1, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, num).forEach(i -> {
            User user = new User(userName + i);
            UserProfile profile = new UserProfile(user, "location" + i);
            cache.put(user, profile);
        });
    }

    @GetMapping(value = "/api/gk/common/weakhashmap/right")
    public void right(@RequestParam long num) {
        String userName = "oom";
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() ->
                log.info("size2:{}", cache2.size()), 1, 3, TimeUnit.SECONDS);
        LongStream.rangeClosed(1, num).forEach(i -> {
            User user = new User(userName + i);
            //这次，我们使用弱引用来包装UserProfile
            cache2.put(user, new WeakReference(new UserProfile(user, "location" + i)));
        });
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class User {
        private String name;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class UserProfile {
        private User user;
        private String location;
    }
}
