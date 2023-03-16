package org.pcdd;

import org.pcdd.config.MvcConfig;
import org.pcdd.interceptor.MyInterceptor1;
import org.pcdd.interceptor.MyInterceptor2;
import org.pcdd.interceptor.MyInterceptor3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author pc
 */
@RestController
/*@ComponentScan(excludeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        MvcConfig.class,
                        MyInterceptor1.class,
                        MyInterceptor2.class,
                        MyInterceptor3.class
                }
        )
})*/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/")
    public String index(){
        return "ok";
    }

}
