package com.hhplus.commerce.spring.api.common.presentation.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadGateWayErrorCode {
    PAYMENT_BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "50200000", "결제 시스템 연동실패!");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
