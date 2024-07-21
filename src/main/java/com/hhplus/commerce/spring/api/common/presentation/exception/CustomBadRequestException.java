package com.hhplus.commerce.spring.api.common.presentation.exception;

import com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode;
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
