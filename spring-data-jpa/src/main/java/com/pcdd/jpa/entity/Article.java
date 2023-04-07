package com.pcdd.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pcdd
 * @date 2021/3/21 18:07
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class) // 审计功能
public class Article implements Serializable {

    @Id
    // IDENTITY 与 AUTO 均为主键自增，前者由数据库控制，后者由程序控制（通过xxx_seq表）
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String content;
    private String author;
    @CreatedDate
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;
    @CreatedBy
    private String createOperator;
    @LastModifiedBy
    private String modifyOperator;

}
