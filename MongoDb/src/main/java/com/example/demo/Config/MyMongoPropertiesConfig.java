package com.example.demo.Config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "my.mongo")
@Data
public class MyMongoPropertiesConfig
{
    private String uri;
    private String database;
}
