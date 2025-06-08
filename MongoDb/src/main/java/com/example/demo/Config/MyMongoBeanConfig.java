package com.example.demo.Config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MyMongoBeanConfig {

    @Bean
    public MongoClient myMongoClient(MyMongoPropertiesConfig myMongoPropertiesConfig){
        return MongoClients.create(myMongoPropertiesConfig.getUri());
    }

    @Bean
    public MongoTemplate myMongoTemplate(MongoClient mongoClient, MyMongoPropertiesConfig myMongoPropertiesConfig){
        return new MongoTemplate(mongoClient, myMongoPropertiesConfig.getDatabase());
    }
}
