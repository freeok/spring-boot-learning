package work.pcdd.aop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.aop.annotation.MyAnnotation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 1907263405@qq.com
 * @date 2021/5/9 21:31
 */
@RestController
public class BaseController {

    @GetMapping("/api1")
    public Map<String, Object> api1() {
        Map<String, Object> map = new HashMap<>();
        map.put("nowTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return map;
    }

    @MyAnnotation
    @GetMapping("/api2/{par}")
    public String api2(@PathVariable String par) {
        return "api2 调用成功 par=" + par;
    }
}
