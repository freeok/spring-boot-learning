package com.pcdd.jpa.audit;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author pcdd
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
