package com.example.mission4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Mission4Application {

    public static void main(String[] args) {
        SpringApplication.run(Mission4Application.class, args);
    }

}