package com.hhplus.commerce.spring.payment.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentGatewayClient paymentGatewayClient;
}
