package com.hhplus.commerce.spring.old.api.user.infrastructure.database;

import com.hhplus.commerce.spring.old.api.user.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {

}
