package work.pcdd.aop.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * 扫描方法
 *
 * @author 1907263405@qq.com
 * @date 2021/5/9 21:39
 */
@Slf4j
@Aspect
// @Component
public class LogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * execution函数用于匹配方法执行的连接点，语法为：
     * execution(方法修饰符(可选)  返回类型  方法名  参数  异常模式(可选))
     * 参数部分允许使用通配符：
     * *  匹配任意字符，但只能匹配一个元素
     * .. 匹配任意字符，可以匹配任意多个元素，表示类时，必须和*联合使用
     * +  必须跟在类名后面，如Horseman+，表示类本身和继承或扩展指定类的所有类
     */
    @Pointcut("execution(public * work.pcdd.aop.controller.*.*(..))")
    private void webLog() {
    }

    /**
     * 前置增强
     */
    @Before("webLog()")
    public void doBefore(JoinPoint jp) {
        log.info("===================== doBefore begin ======================");
        // 接收到请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求内容
        log.info("URL : {}", request.getRequestURL());
        log.info("HTTP方法 : {}", request.getMethod());
        log.info("IP地址 : {}", request.getRemoteAddr());
        log.info("类的方法 : {}.{}", jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
        log.info("方法参数 : {}", Arrays.toString(jp.getArgs()));
        log.info("===================== doBefore end ======================");
    }

    /**
     * 后置增强
     */
    @AfterReturning(pointcut = "webLog()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("===================== doAfterReturning begin ======================");
        // 处理完请求，返回内容
        log.info("方法的返回值 : " + result);
        log.info("===================== doAfterReturning end ======================");
    }

    /**
     * 后置最终通知，最终增强，不管是抛出异常或者正常退出都会执行
     */
    @After("webLog()")
    public void doAfter(JoinPoint jp) {
        log.info("===================== doAfter begin ======================");
        log.info("方法最后执行.....");
        log.info("===================== doAfter end ======================");
    }

    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        log.info("===================== doAround begin ======================");
        startTime.set(System.currentTimeMillis());
        try {
            Object o = pjp.proceed();
            log.info("方法环绕proceed，结果是 :" + o);
            log.info("方法执行耗时：" + (System.currentTimeMillis() - startTime.get()) + " ms");
            log.info("===================== doAround end ======================");
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing(pointcut = "webLog()", throwing = "ex")
    public void doThrows(JoinPoint jp, Exception ex) {
        log.info("=====================doThrows======================");
        log.info("方法异常时执行\n发生的异常：" + ex.getClass().getName() + "\n异常信息：" + ex.getMessage());
        log.info("=====================doThrows======================");
    }

}
