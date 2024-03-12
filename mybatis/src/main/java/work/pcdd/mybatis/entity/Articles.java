package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
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
