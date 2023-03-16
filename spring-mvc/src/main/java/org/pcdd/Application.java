package org.pcdd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pcdd
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
    public String index() {
        return "ok";
    }

}
