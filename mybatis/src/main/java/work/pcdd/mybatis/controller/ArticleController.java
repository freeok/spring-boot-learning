package work.pcdd.mybatis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import work.pcdd.mybatis.entity.Article;
import work.pcdd.mybatis.mapper.ArticleMapper;

import java.util.List;

/**
 * @author 1907263405@qq.com
 * @since 2021-05-13
 */
@RestController
public class ArticleController {
    @Autowired
    ArticleMapper articleMapper;

    @GetMapping("/")
    public List<Article> list() {
        return articleMapper.list();
    }

}
