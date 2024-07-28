package com.hhplus.commerce.spring.api.common.presentation.exception;

import com.hhplus.commerce.spring.api.common.presentation.exception.code.BadGateWayErrorCode;
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
