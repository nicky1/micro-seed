package com.waffle.controller;

import com.waffle.model.mongodb.Work;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2019-10-11 23:20
 */
@RestController
public class TestWebFluxController {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping(value = "/api/webflux/test/save")
    public ResponseEntity save() {

        Work work = Work.builder().name("张三111").createTime(System.currentTimeMillis()).build();
        mongoTemplate.save(work);

        Work work2 = Work.builder().name("张三222").createTime(System.currentTimeMillis()).build();
        reactiveMongoTemplate.insert(work2).subscribe();
        return ResponseEntity.ok().build();
    }
}
