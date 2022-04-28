package com.example.module04formlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Module04FormLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Module04FormLoginApplication.class, args);
    }

}
