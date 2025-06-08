package com.example.demo.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class MongoCustomRepositoryImpl<T,ID> implements MongoCustomRepository<T,ID> {

    private final MongoTemplate mongoTemplate;
    private Class<T> entityClass;


    @Override
    public T save(T entity) {
        return mongoTemplate.save(entity);
    }

    @Override
    public Optional<T> findById(ID id) {
        return Optional.ofNullable(mongoTemplate.findById(id,entityClass));
    }

    @Override
    public List<T> findAll() {
        return mongoTemplate.findAll(entityClass);
    }


    }
