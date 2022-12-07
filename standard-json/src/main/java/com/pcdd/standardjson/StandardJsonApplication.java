package com.pcdd.standardjson;

import com.pcdd.standardjson.common.api.ApiResult;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@SpringBootApplication
public class StandardJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(StandardJsonApplication.class, args);
    }

    @GetMapping("/")
    public LocalDateTime index() {
        return LocalDateTime.now();
    }

    @GetMapping("/2")
    public ApiResult<String> index2() {
        return ApiResult.success("若返回值类型为ApiResult，则不会被再次包装");
    }

}
