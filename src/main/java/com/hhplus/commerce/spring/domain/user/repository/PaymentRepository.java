package com.hhplus.commerce.spring.domain.user.repository;

import com.hhplus.commerce.spring.old.api.user.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
