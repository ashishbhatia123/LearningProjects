package com.example.demo.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRepositoryImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepositoryImpl;

    public User Save(User user){
        return userRepositoryImpl.save(user);
    }

    public Optional<User> findById(String id){
        return userRepositoryImpl.findById(id);
    }

    public List<User> findAll() {
        return userRepositoryImpl.findAll();
    }
}
