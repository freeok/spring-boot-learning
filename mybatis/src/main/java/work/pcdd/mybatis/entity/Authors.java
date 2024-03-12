package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Authors 和 Articles 是一对多
 * 一个作者可以有多篇文章，作者是主表
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class Authors implements Serializable {

    private Integer authorId;
    private String name;
    private List<Articles> articles;

}
