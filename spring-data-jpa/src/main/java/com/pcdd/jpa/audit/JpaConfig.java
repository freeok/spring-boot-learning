package com.pcdd.jpa.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author pcdd
 * create at 2023/04/07 05:39
 */
@EnableJpaAuditing // 启用JPA审计功能
@Configuration(proxyBeanMethods = false)
public class JpaConfig {

    @Autowired
    CustomerRepository customerRepository;

    /**
     * 泛型 T 必须和标有 @CreatedBy、@LastModifiedBy 属性的类型一致
     * eg：String 保存用户名，Long 保存 ID
     */
    @Bean
    public AuditorAware<Customer> auditorProvider() {
        // 假设 id 为 1 表示管理员，由于外键约束，管理员必须已在数据库
        return () -> customerRepository.findById(1L);
    }

}
