package work.pcdd.validator.common.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author pcdd
 * @date 2021/5/15 19:58
 */
@Slf4j
public class CustomConstraintValidator implements ConstraintValidator<CustomConstraint, String> {

    @Override
    public void initialize(CustomConstraint constraintAnnotation) {
        log.info("自定义验证类启动");
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return "专科".equals(s) || "本科".equals(s) || "研究生".equals(s);
    }

}
