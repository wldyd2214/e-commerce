package com.hhplus.commerce.spring.presentation.common.exception;

import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomBadRequestException extends RuntimeException {
    private BadRequestErrorCode errorCode;

    public CustomBadRequestException(BadRequestErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
