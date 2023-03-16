package com.pcdd.mybatisplus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcdd.mybatisplus.entity.Person;
import com.pcdd.mybatisplus.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pcdd
 * @date 2021-12-28
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPersonService personService;

    /**
     * 分页查询
     */
    @GetMapping("/list")
    public Object list() {
        return personService.page(Page.of(0, 3));
    }

    /**
     * 新增或更新
     */
    @PostMapping
    public Object addOrUpdate(@RequestBody Person person) {
        return personService.saveOrUpdate(person);
    }
}
