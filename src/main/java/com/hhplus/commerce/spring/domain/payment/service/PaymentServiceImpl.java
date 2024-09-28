package com.hhplus.commerce.spring.domain.payment.service;

import com.hhplus.commerce.spring.domain.payment.dto.PaymentCommand;
import com.hhplus.commerce.spring.domain.payment.dto.response.PaymentSystemClientResponse;
import com.hhplus.commerce.spring.infrastructure.payment.client.PaymentSystemClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentSystemClient client;

    public String sendPayment(PaymentCommand.Payment command) {
        PaymentSystemClientResponse response = client.sendPayment(command.getUserId(), command.getAmount());
        return response.getTransactionId();
    }
}
