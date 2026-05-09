package com.example.umc10th;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @EntityListeners 사용을 위함
public class Mission4Application {

    public static void main(String[] args) {
        SpringApplication.run(Umc10thApplication.class, args);
    }

}
