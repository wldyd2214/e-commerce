package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.entity.Payment;
import com.hhplus.commerce.spring.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository jpaRepository;

    @Override
    public Payment save(Payment payment) {
        return jpaRepository.save(payment);
    }
}
