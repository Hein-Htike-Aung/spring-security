package com.example.module02jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Module02JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(Module02JwtApplication.class, args);
    }

}
