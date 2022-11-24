package com.pcdd.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pc
 */
@SpringBootApplication
/*
@SpringBootApplication等价于3个注解：
@Configuration
@EnableAutoConfiguration
@ComponentScan*/
public class SpringDataJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }
}
