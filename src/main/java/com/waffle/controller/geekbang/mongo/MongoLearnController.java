package com.waffle.controller.geekbang.mongo;

import com.waffle.model.mongodb.Work;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yixiaoshuang
 * @date 2020/4/18 18:42
 */
@RestController
public class MongoLearnController {

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/api/gk/mongo/readPre/w")
    public ResponseEntity readPreferenceW(@RequestParam String name) {
        Work work = Work.builder()
                .id(ObjectId.get().toString())
                .name(name)
                .createTime(System.currentTimeMillis())
                .build();
        mongoTemplate.save(work);

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/api/gk/mongo/readPre/r")
    public ResponseEntity readPreferenceR(@RequestParam String id) {
        Work work = mongoTemplate.findById(id, Work.class);

        return ResponseEntity.ok(work);
    }
}
