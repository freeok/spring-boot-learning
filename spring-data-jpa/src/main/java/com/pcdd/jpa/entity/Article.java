package com.pcdd.jpa.entity;

import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    @NotEmpty(message = "标题不能为空")
    private String title;

    @Size(max = 255)
    private String description;

    @Column(nullable = false)
    @NotEmpty(message = "内容不能为空")
    private String content;

    @NotEmpty(message = "作者不能为空")
    private String author;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
