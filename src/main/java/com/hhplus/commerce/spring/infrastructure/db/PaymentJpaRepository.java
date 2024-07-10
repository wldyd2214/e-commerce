package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

}
