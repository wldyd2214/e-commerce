package com.hhplus.commerce.spring.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BadRequestErrorCode implements BaseErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청 파라미터"),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "40000001", "잘못된 이메일 형식"),
    AMOUNT_MUST_BE_POSITIVE(HttpStatus.BAD_REQUEST, "40000002", "충전 포인트 금액은 0 보다 커야 합니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
