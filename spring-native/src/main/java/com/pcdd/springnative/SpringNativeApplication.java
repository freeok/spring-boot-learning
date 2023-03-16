package com.pcdd.springnative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class SpringNativeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNativeApplication.class, args);
    }

    @GetMapping("/")
    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }

}