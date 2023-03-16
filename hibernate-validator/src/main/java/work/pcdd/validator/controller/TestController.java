package work.pcdd.validator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.validator.entity.Student;

/**
 * @author pcdd
 * @date 2021/4/24 20:39
 */
@Slf4j
@RestController
public class TestController {

    @PostMapping("/test")
    public Student test3(@Validated @RequestBody Student student) {
        log.info(student.toString());
        return student;
    }

}




