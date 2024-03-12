package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Article 和 Tag 是多对多
 * 一篇文章可以有多个标签，一个标签也可以有多篇文章
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class Article implements Serializable {

    private Integer articleId;
    private String title;
    private String content;
    private Integer authorId;
    /**
     * 多对一，多篇文章是一个作者
     */
    private Author author;
    /**
     * 多对多，一篇文章有多个标签，一个标签有多篇文章
     */
    private List<Tag> tags;

}
