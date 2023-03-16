package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author pcdd
 * @date 2021-05-13
 */
@Data
public class Article implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String title;
    private String description;
    private String content;
}
