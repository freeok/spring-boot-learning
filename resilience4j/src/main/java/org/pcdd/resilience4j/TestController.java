package org.pcdd.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author pcdd
 */
@Slf4j
@RestController
public class TestController {

    /**
     * 限流测试
     */
    @GetMapping("/t1")
    @RateLimiter(name = "backendB", fallbackMethod = "fallback")
    public ResponseEntity<R> t1() {
        log.info("RateLimiter API");
        return ResponseEntity.ok(new R(200, "RateLimiter API 调用成功"));
    }

    /**
     * 熔断测试
     */
    @GetMapping("/t2")
    @CircuitBreaker(name = "backendB", fallbackMethod = "fallback")
    public ResponseEntity<R> t2() {
        log.info("CircuitBreaker API");
        int randomNum = new Random().nextInt(10);
        if (randomNum >= 5) {
            throw new RuntimeException("模拟异常发生");
        }
        return ResponseEntity.ok(new R(200, "CircuitBreaker API 调用成功"));
    }

    /**
     * 重试测试
     */
    @GetMapping("/t3")
    @Retry(name = "backendA", fallbackMethod = "fallback")
    public ResponseEntity<R> t3() {
        log.info("Retry API");
        int randomNum = new Random().nextInt(10);
        if (randomNum >= 5) {
            throw new RuntimeException("模拟异常发生");
        }
        return ResponseEntity.ok(new R(200, "Retry API 调用成功"));
    }

    public ResponseEntity<R> fallback(Throwable t) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (t.getClass().getPackageName().startsWith("io.github.resilience4j.")) {
            status = HttpStatus.TOO_MANY_REQUESTS;
        }
        return ResponseEntity
                .status(status)
                .body(new R(status.value(), t.getMessage()));
    }

}
