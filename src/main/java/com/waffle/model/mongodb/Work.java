package com.waffle.model.mongodb;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author yixiaoshuang
 * @date 2019-10-11 23:24
 */
@Document(collection = "work")
@Builder
@Data
public class Work {

    @Id
    private String id;

    private String name;

    private long createTime;


}
