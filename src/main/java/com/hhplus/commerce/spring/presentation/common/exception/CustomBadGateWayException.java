package com.hhplus.commerce.spring.presentation.common.exception;

import com.hhplus.commerce.spring.presentation.common.exception.code.BadGateWayErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomBadGateWayException extends RuntimeException {
    private BadGateWayErrorCode errorCode;

    public CustomBadGateWayException(BadGateWayErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
