package org.pcdd.config;

import org.pcdd.interceptor.MyInterceptor1;
import org.pcdd.interceptor.MyInterceptor2;
import org.pcdd.interceptor.MyInterceptor3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author pcdd
 * @date 2021/5/9 22:19
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor1()).addPathPatterns("/**");
        registry.addInterceptor(myInterceptor2()).addPathPatterns("/**");
        registry.addInterceptor(myInterceptor3()).addPathPatterns("/**");
    }

    @Bean
    public MyInterceptor1 myInterceptor1() {
        return new MyInterceptor1();
    }

    @Bean
    public MyInterceptor2 myInterceptor2() {
        return new MyInterceptor2();
    }

    @Bean
    public MyInterceptor3 myInterceptor3() {
        return new MyInterceptor3();
    }

}
