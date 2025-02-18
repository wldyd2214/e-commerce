package com.hhplus.commerce.spring.infrastructure.payment.client;

import com.hhplus.commerce.spring.domain.payment.client.PaymentSystemClient;
import com.hhplus.commerce.spring.domain.payment.domain.Payment;
import com.hhplus.commerce.spring.domain.payment.dto.type.PaymentState;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
public class PaymentSystemClientImpl implements PaymentSystemClient {

    @Override
    public Payment sendPayment(Long userId, BigDecimal amount) {

        // 결제 요청을 시뮬레이션하여 응답 생성
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            log.error("{} 사용자 결제 실패", userId);
            throw new CustomBadGateWayException(BadGateWayErrorCode.PAYMENT_BAD_GATEWAY);
        }

        // 성공적으로 결제 요청이 처리된 경우 임의의 트랜잭션 ID 생성
        String transactionId = "txn-" + System.currentTimeMillis();

        return new Payment(transactionId, PaymentState.SUCCESS);
    }

    // WebClient 예시 코드
//    @Override
//    public TaxScrapUser fetchScrapInfo(Fetch fetch) {
//        try {
//            ScrapClientResponse response =
//                webClient.post()
//                    .uri("/scrap")
//                    .headers(httpHeaders -> {
//                        httpHeaders.add(ScrapHttpHeaderCode.X_API_KEY.getValue(), apiKey);
//                    })
//                    .bodyValue(fetch)
//                    .retrieve()
//                    .bodyToMono(ScrapClientResponse.class)
//                    .block();
//
//            return responseMapper.toEntity(response);
//        } catch (WebClientResponseException e) {
//            log.warn("[fetchScrapInfo] - Warn: {}", e.getMessage());
//            throw new CustomInternalServerException(InternalServerErrorCode.INTERNAL_SERVER_ERROR);
//        } catch (Exception e) {
//            log.error("[fetchScrapInfo] - Error: {}", e.getMessage());
//            throw new CustomInternalServerException(InternalServerErrorCode.INTERNAL_SERVER_ERROR);
//        }
//    }
}
