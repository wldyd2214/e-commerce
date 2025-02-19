package com.hhplus.commerce.spring.domain.payment.client;

import com.hhplus.commerce.spring.domain.payment.domain.Payment;
import java.math.BigDecimal;

public interface PaymentSystemClient {

    public Payment sendPayment(Long userId, BigDecimal amount);
}
