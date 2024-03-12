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
public class Authors implements Serializable {

    private Integer authorId;
    private String name;
    private List<Articles> articles;

}
