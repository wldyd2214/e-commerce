package com.hhplus.commerce.spring.domain.payment.service;

import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;

public interface PaymentService {
    String sendPayment(PaymentCommand.Payment command);
}
