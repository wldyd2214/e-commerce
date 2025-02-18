package com.hhplus.commerce.spring.infrastructure.user.database;

import com.hhplus.commerce.spring.old.api.user.model.Payment;
import com.hhplus.commerce.spring.domain.user.repository.PaymentRepository;
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
