package com.hhplus.commerce.spring.old.presentation.common.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ConflictErrorCode {

    USER_POINT_CHARGE_CONFLICT(HttpStatus.CONFLICT, "40900000", "사용자 포인트 충전 충돌"),
    USER_POINT_DEDUCTION_CONFLICT(HttpStatus.CONFLICT, "40900001", "사용자 포인트 차감 충돌");

    private HttpStatus httpStatus;
    private String code;
    private String message;
}
