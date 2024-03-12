package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Articles 和 Tags 是多对多
 * 一篇文章可以有多个标签，一个标签也可以有多篇文章
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class Articles implements Serializable {

    private Integer articleId;
    private String title;
    private String content;
    private Integer authorId;

}
