package com.hhplus.commerce.spring.repository;

import com.hhplus.commerce.spring.model.entity.Payment;

public interface PaymentRepository {

    Payment save(Payment payment);
}
