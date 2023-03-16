package work.pcdd.validator.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import work.pcdd.validator.common.validator.CustomConstraint;

import java.io.Serializable;

/**
 * @author pcdd
 * @date 2021/5/15 19:14
 */
@Data
public class Student implements Serializable {
    @NotEmpty(message = "姓名不能空")
    private String name;

    @NotNull(message = "年龄不能为空")
    @Range(min = 0, max = 200, message = "年龄不合法")
    private Integer age;

    @NotNull(message = "学历不能为空")
    @CustomConstraint
    private String education;
}


