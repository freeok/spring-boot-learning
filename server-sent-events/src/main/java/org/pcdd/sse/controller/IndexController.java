package org.pcdd.sse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author pcdd
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
