package com.pcdd.jpa.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.jpa.domain.AbstractAuditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * AbstractAuditable 的使用
 *
 * @author pcdd
 * create at 2023/04/08 13:24
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
// 坑：AbstractAuditable<U, PK> 中的 U 必须为引用类型，因为字段标记了@ManyToOne
public class Customer extends AbstractAuditable<Customer, Long> {

    // 省略了 id、createdBy、createdDate、lastModifiedBy、lastModifiedDate，因为已被继承
    private String name;

}
