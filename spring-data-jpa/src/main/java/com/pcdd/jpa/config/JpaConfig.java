package com.pcdd.jpa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author pcdd
 * create at 2023/04/07 05:39
 */
@EnableJpaAuditing // 启用JPA审计功能
@Configuration(proxyBeanMethods = false)
public class JpaConfig {

    /**
     * 注解 @CreatedBy、@LastModifiedBy 配置
     * 泛型可以为String保存用户名，或者为Long保存用户ID
     */
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("pcdd");
    }

}
