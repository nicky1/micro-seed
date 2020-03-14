package com.waffle.controller;

import com.waffle.model.mongodb.Work;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2019-10-11 23:20
 */
@Controller
@Slf4j
public class TestWebFluxController {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @GetMapping(value = "/api/webflux/test/save")
    public @ResponseBody
    ResponseEntity save() {
        log.info("3333");
        Work work2 = Work.builder().name("张三422234").createTime(System.currentTimeMillis()).build();
        reactiveMongoTemplate.insert(work2).subscribe();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/webflux/test/reactive/save")
    public @ResponseBody
    Mono reSave() {

        Work work2 = Work.builder().name("张三2223456").createTime(System.currentTimeMillis()).build();
        return reactiveMongoTemplate.insert(work2);
    }
}
