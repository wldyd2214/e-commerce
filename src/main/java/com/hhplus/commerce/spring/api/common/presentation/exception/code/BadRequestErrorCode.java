package com.hhplus.commerce.spring.api.common.presentation.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청 파라미터"),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "40000001", "잔액 부족");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
