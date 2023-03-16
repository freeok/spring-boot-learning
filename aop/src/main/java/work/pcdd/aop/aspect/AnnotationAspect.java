package work.pcdd.aop.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * 扫描注解
 *
 * @author pcdd
 */
@Slf4j
@Aspect
@Order(-99)
@Component
public class AnnotationAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("@annotation(work.pcdd.aop.annotation.MyAnnotation))")
    private void myAnnotationCheck() {
    }

    @Before("myAnnotationCheck()")
    public void doBefore(JoinPoint jp) {
        System.out.println("=====================doBefore======================");
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        log.info("URL : {}", request.getRequestURL());
        log.info("HTTP方法 : {}", request.getMethod());
        log.info("IP地址 : {}", request.getRemoteAddr());
        log.info("类的方法 : {}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        log.info("方法参数 : {}", Arrays.toString(jp.getArgs()));
        System.out.println("=====================doBefore======================");
    }

    /**
     * 后置增强
     */
    @AfterReturning(pointcut = "myAnnotationCheck()", returning = "result")
    public void doAfterReturning(Object result) {
        System.out.println("=====================doAfterReturning======================");
        log.info("方法的返回值 : {}", result);
        log.info("耗时 : {}ms", (System.currentTimeMillis() - startTime.get()));
        System.out.println("=====================doAfterReturning======================");
    }
}
