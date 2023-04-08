package com.pcdd.jpa.config;

import com.pcdd.jpa.entity.Article;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义审计监听器，监听 Article 实体类
 *
 * @author pcdd
 * create at 2023/04/08 14:01
 */
@Slf4j
public class ArticleAuditingListener {

    @PostLoad
    private void postLoad(Article entity) {
        log.info("查询后做些什么 {}", entity);
    }

    @PostPersist
    private void postPersist(Article entity) {
        log.info("插入后做些什么 {}", entity);
    }

    @PostUpdate
    private void postUpdate(Article entity) {
        log.info("更新后做些什么 {}", entity);
    }

    @PostRemove
    private void postRemove(Article entity) {
        log.info("删除后做些什么 {}", entity);
    }

}
