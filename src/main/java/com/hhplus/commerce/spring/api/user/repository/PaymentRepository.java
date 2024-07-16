package com.hhplus.commerce.spring.api.user.repository;

import com.hhplus.commerce.spring.api.user.model.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
