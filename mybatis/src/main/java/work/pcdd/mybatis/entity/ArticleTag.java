package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * Article 和 Tag 的中间表
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class ArticleTag implements Serializable {

    private Integer articleId;
    private Integer tagId;

}
