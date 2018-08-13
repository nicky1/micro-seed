package com.waffle.service;

/**
 * @Author: yixiaoshuang
 * @Date: 2018/7/4 16:30
 * @Description: spring cache test
 */
public interface CacheTestService {
    String get(Integer id);

    void save(Integer id,String name);

    void delete(Integer id);
}
