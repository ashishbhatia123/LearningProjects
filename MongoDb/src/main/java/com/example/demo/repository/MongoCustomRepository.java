package com.example.demo.repository;


import java.util.List;
import java.util.Optional;

public interface MongoCustomRepository<T, ID> {
    T save(T entity);
    Optional<T> findById(ID id);
    List<T> findAll();


}
