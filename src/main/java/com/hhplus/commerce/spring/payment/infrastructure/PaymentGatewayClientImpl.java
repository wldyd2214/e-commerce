package com.hhplus.commerce.spring.payment.infrastructure;

import com.hhplus.commerce.spring.payment.domain.service.PaymentGatewayClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PaymentGatewayClientImpl implements PaymentGatewayClient {

    private final WebClient paymentGatewayWebClient;

//    public PaymentGatewayTransactionInfo searchTransaction(String transactionId) {
//        PaymentGatewayTransactionInfo info = search(transactionId);
//        info.setMid(mid);
//        return info;
//    }
//
//    private PaymentGatewayTransactionInfo search(String transactionId) {
//        return paymentGatewayWebClient.get()
//            .uri(
//                uriBuilder ->
//                    uriBuilder.path("/payment/search/transaction/{tid}").build(transactionId)
//            )
//            .retrieve()
//            .bodyToMono(PaymentGatewayTransactionInfo.class)
//            .block();
//    }
}
