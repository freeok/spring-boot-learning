package com.pcdd.jpa.audit;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pcdd
 * @date 2021/3/21 19:23
 */
public interface AuditEntityRepository extends JpaRepository<AuditEntity, Long> {
}
