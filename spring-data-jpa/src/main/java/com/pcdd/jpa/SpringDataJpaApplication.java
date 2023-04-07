package com.pcdd.jpa;

import com.pcdd.jpa.entity.Article;
import com.pcdd.jpa.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/">文档</a>
 *
 * @author pcdd
 */
@SpringBootApplication
public class SpringDataJpaApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Autowired
    ArticleRepository articleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Article article = new Article();
        article.setTitle("gourmet food");
        // 存入数据库的emoji为?，但通过JPA查询出来的正常
        article.setContent("3C🍕a啊🍔吧🍟从🌭的");
        articleRepository.save(article);
    }
}
