
package com.pcdd.jpa.controller;

import com.pcdd.jpa.entity.Article;
import com.pcdd.jpa.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 1907263405@qq.com
 * @date 2021/3/21 19:31
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/list")
    public List<Article> getList(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable).getContent();
    }

    @GetMapping("/{id}")
    public Article getOne(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Article save(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PostMapping("/batch")
    public String batchSave() {
        List<Article> list = new ArrayList<>();
        // 插入1k条数据
        for (int i = 1; i <= 1000; i++) {
            Article article = new Article();
            article.setTitle("标题" + i);
            article.setDescription("描述" + i);
            article.setContent("内容" + i);
            article.setAuthor("作者" + i);
            list.add(article);
        }
        articleRepository.saveAll(list);
        return "批量保存成功";
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Long id) {
        articleRepository.deleteById(id);
        return "删除成功";
    }

    @DeleteMapping("/batch")
    public String batchDelete(List<Long> ids) {
        articleRepository.deleteAllById(ids);
        return "批量删除成功";
    }

}
