package com.pcdd.jpa.repository;

import com.pcdd.jpa.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author 1907263405@qq.com
 * @date 2021/3/21 19:23
 */
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

}
