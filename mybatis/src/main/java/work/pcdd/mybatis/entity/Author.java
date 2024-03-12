package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Author 和 Article 是一对多
 * 一个作者可以有多篇文章，作者是主表
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class Author implements Serializable {

    private Integer authorId;
    private String name;
    /**
     * 一对多
     */
    private List<Article> articles;

}
