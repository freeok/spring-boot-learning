package work.pcdd.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author pcdd
 * @since 2024-03-12
 */
@Data
public class Tags implements Serializable {

    private Integer tagId;
    private String name;

}
