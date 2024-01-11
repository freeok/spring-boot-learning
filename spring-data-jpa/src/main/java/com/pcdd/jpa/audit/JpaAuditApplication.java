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
public class JpaAuditApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(JpaAuditApplication.class, args);
    }

    @Autowired
    AuditEntityRepository auditEntityRepository;
    @Autowired
    CustomerRepository customerRepository;

    // 自定义审计监听器测试
    @Override
    public void run(ApplicationArguments args) {
        // 新增审计者
        Customer customer = new Customer();
        customer.setName("admin");
        customerRepository.save(customer);

        // 新增，触发 @PrePersist、@PostPersist
        AuditEntity auditEntity = new AuditEntity();
        // 存入数据库的emoji为?，但通过JPA查询出来的正常
        auditEntity.setContent("你🍕好🍔世🍟界🌭");
        AuditEntity save = auditEntityRepository.save(auditEntity);

        // 修改，触发 @preUpdate、@postUpdate
        save.setContent("修改后的内容");
        auditEntityRepository.save(save);

        // 查询，触发 @postLoad
        auditEntityRepository.findById(save.getId());

        // 删除，触发 @preRemove、@postRemove
        auditEntityRepository.delete(save);
    }

}
