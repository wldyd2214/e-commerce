package com.hhplus.commerce.spring.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ConflictErrorCode implements BaseErrorCode {
    CONFLICT(HttpStatus.CONFLICT, "40900000", "이미 존재하는 리소스"),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "40900001", "이미 사용중인 이메일입니다: {0}");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
