package com.pcdd.springnative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author pcdd
 */
@RestController
@SpringBootApplication
public class SpringNativeApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        SpringApplication.run(SpringNativeApplication.class, args);
    }

    @GetMapping("/")
    public Map<String, Object> getCurrentTime() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("message", "Hello World!");
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return map;
    }

}