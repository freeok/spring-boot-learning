package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author pcdd
 */
@Controller
public class PageController {

    @GetMapping("/index")
    public ModelAndView doJsp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("show", "SpringBoot整合JSP");
        System.out.println(modelAndView);
        return modelAndView;
    }

}
