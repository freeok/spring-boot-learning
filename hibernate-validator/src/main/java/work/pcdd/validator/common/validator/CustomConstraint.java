package work.pcdd.validator.common.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义参数校验注解
 *
 * @author pcdd
 * @date 2021/5/15 19:54
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomConstraintValidator.class)
public @interface CustomConstraint {
    String message() default "学历必须是专科、本科、研究生中的一项";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
