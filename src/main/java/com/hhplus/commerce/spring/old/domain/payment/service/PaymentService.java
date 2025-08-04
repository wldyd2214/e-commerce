package com.hhplus.commerce.spring.old.domain.payment.service;

import com.hhplus.commerce.spring.old.domain.payment.client.PaymentSystemClient;
import com.hhplus.commerce.spring.old.domain.payment.domain.Payment;
import com.hhplus.commerce.spring.old.domain.payment.dto.PaymentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentService {

    private final PaymentSystemClient client;

    public String sendPayment(PaymentCommand.Payment command) {

        Payment payment = client.sendPayment(command.getUserId(), command.getAmount());

        return payment.getTransactionId();
    }
}
