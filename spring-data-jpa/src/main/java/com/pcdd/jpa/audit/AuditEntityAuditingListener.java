package com.pcdd.jpa.audit;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义审计监听器，监听 AuditEntity 实体类
 *
 * @author pcdd
 * create at 2023/04/08 14:01
 */
@Slf4j
public class AuditEntityAuditingListener {

    @PostLoad
    private void postLoad(AuditEntity entity) {
        log.info("查询后做些什么 {}", entity);
    }

    @PostPersist
    private void postPersist(AuditEntity entity) {
        log.info("插入后做些什么 {}", entity);
    }

    @PostUpdate
    private void postUpdate(AuditEntity entity) {
        log.info("更新后做些什么 {}", entity);
    }

    @PostRemove
    private void postRemove(AuditEntity entity) {
        log.info("删除后做些什么 {}", entity);
    }

}
