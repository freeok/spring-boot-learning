package com.pcdd.jpa.repository;

import com.pcdd.jpa.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pcdd
 * @date 2021/3/21 19:23
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * JPA 会转换为以下查询：select * from article where author = ? and title = ?
     * <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods">详见文档</a>
     */
    Article findByTitleAndAuthor(String title, String author);

}
