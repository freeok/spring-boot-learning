package com.pcdd.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 1907263405@qq.com
 * @date 2021/3/21 18:07
 */
@Data
@Entity
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    @Column(nullable = false)
    private String content;
    private String author;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
