package com.pcdd.jpa.simple;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author pcdd
 * @date 2021/3/21 18:07
 */
@Data
@Entity
public class Article {

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

}
