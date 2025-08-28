package com.hhplus.commerce.spring.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum NotFoundErrorCode implements BaseErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND, "40400000", "리소스 정보 미존재"),
    USER_NOT_FOUNT(HttpStatus.NOT_FOUND, "40400001", "회원 정보 미존재"),
    PASSWORD_NOT_MATCH(HttpStatus.NOT_FOUND, "40400002", "아이디 또는 비밀번호 불일치");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
