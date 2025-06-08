package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends MongoCustomRepositoryImpl<User,String> {

    private final MongoTemplate mongoTemplate;

    public UserRepositoryImpl(MongoTemplate mongoTemplate, MongoTemplate mongoTemplate1) {
        super(mongoTemplate, User.class);
        this.mongoTemplate = mongoTemplate1;
    }
}
