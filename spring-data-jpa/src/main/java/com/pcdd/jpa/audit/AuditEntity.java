package com.pcdd.jpa.audit;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @author pcdd
 * @date 2021/3/21 18:07
 */
@Data
@Entity
@EntityListeners({AuditingEntityListener.class, AuditEntityAuditingListener.class}) // 指定审计监听器
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @CreatedDate
    private LocalDateTime createTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

    @ManyToOne
    @CreatedBy
    private Customer createOperator;

    @ManyToOne
    @LastModifiedBy
    private Customer modifyOperator;

}
