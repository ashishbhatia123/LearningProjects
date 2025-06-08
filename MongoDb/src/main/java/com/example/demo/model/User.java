package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Document(collection="users")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String email;
}
