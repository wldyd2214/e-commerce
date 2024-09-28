package com.hhplus.commerce.spring.infrastructure.payment.client;

import com.hhplus.commerce.spring.domain.payment.dto.response.PaymentSystemClientResponse;
import com.hhplus.commerce.spring.domain.payment.dto.type.PaymentState;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class PaymentSystemClient {

    public PaymentSystemClientResponse sendPayment(Long userId, BigDecimal amount) {

        // 결제 요청을 시뮬레이션하여 응답 생성
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("{} 사용자 결제 실패", userId);
            throw new CustomBadGateWayException(BadGateWayErrorCode.PAYMENT_BAD_GATEWAY);
        }

        // 성공적으로 결제 요청이 처리된 경우
        String transactionId = "txn-" + System.currentTimeMillis(); // 임의의 트랜잭션 ID 생성
        return new PaymentSystemClientResponse(transactionId, PaymentState.SUCCESS);
    }
}
