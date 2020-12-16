package com.waffle.common.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

/**
 * @author yixiaoshuang
 * @date 2019-09-29 20:22
 */
@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.uri}")
    private String uri;


    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate getDefaultMongoTemplate() {
        return new MongoTemplate(mongoDbFactory(uri));
    }


    @Bean(name = "reactiveMongoTemplate")
    public ReactiveMongoTemplate getReactiveMongoTemplate() throws Exception {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory(uri));
    }

    /**
     * Method that creates MongoDbFactory Common to both of the MongoDb
     * connections
     */
    private MongoDbFactory mongoDbFactory(String uri) {
        return new SimpleMongoDbFactory(new MongoClientURI(uri));
    }

    private ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory(String uri) throws Exception {
        return new SimpleReactiveMongoDatabaseFactory(new ConnectionString(uri));
    }
}
