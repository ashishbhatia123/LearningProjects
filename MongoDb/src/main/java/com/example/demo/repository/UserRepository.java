package com.example.demo.repository;

import com.example.demo.model.User;

public interface UserRepository extends MongoCustomRepository<User,String> {
}
