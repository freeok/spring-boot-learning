package work.pcdd.aop.annotation;

import java.lang.annotation.*;

/**
 * @author pcdd
 * @date 2021/5/9 22:05
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value() default "";
}
