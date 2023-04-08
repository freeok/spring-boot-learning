package com.pcdd.jpa.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pcdd
 */
@SpringBootApplication
public class SpringDataJpaApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaApplication.class, args);
    }

    @Autowired
    AuditEntityRepository auditEntityRepository;
    @Autowired
    CustomerRepository customerRepository;

    // 自定义审计监听器测试
    @Override
    public void run(ApplicationArguments args) {
        Customer customer = new Customer();
        customer.setName("admin");
        customerRepository.save(customer);
        // 新增
        Customer customer2 = new Customer();
        customer2.setName("张三");
        customerRepository.save(customer2);

        AuditEntity auditEntity = new AuditEntity();
        // 存入数据库的emoji为?，但通过JPA查询出来的正常
        auditEntity.setContent("3C🍕a啊🍔吧🍟从🌭的");

        auditEntityRepository.save(auditEntity);
        AuditEntity save = auditEntityRepository.save(auditEntity);
        save.setContent("修改内容");
        auditEntityRepository.save(save);
        auditEntityRepository.findById(save.getId());
        // articleRepository.delete(save);
    }
}
