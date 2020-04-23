package com.waffle.common.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 服务端本地缓存caffeine配置
 *
 * @author yixiaoshuang
 * @date 2019-10-16 16:36
 */
@Configuration
@EnableCaching
public class CaffeineConfig {

    private static final int DEFAULT_MAXSIZE = 1000;
    private static final int DEFAULT_TTL = 60 * 60;

    /**
     * 创建基于Caffeine的Cache Manager
     *
     * @return cacheManager
     */
    @Bean
    @Primary
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();

        List<CaffeineCache> caches = new ArrayList<>();
        for (Caches c : Caches.values()) {
            caches.add(new CaffeineCache(c.name(),
                    Caffeine.newBuilder().recordStats()
                            .expireAfterWrite(c.getTtl(), TimeUnit.SECONDS)
                            .maximumSize(c.getMaxSize())
                            .build())
            );
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }


    public enum Caches {

        /**
         * 临时缓存学校名称，300s过期时间
         */
        getExamRankNumCache(600, 1000);

        /**
         * 最大数量
         */
        private int maxSize = DEFAULT_MAXSIZE;

        /**
         * 过期时间
         */
        private int ttl = DEFAULT_TTL;


        Caches() {

        }

        Caches(int ttl) {
            this.ttl = ttl;
        }

        Caches(int ttl, int maxSize) {
            this.ttl = ttl;
            this.maxSize = maxSize;
        }


        public int getMaxSize() {
            return maxSize;
        }

        public int getTtl() {
            return ttl;
        }


    }
}
