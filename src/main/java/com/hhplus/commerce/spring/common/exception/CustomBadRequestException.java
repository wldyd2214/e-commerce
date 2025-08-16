package com.hhplus.commerce.spring.common.exception;

import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.common.exception.code.BaseErrorCode;
import java.text.MessageFormat;
import lombok.Getter;

@Getter
public class CustomBadRequestException extends CustomBaseException {

    private final BadRequestErrorCode errorCode;

    public CustomBadRequestException(BadRequestErrorCode errorCode){
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public CustomBadRequestException(BadRequestErrorCode errorCode, Object args){
        super(MessageFormat.format(errorCode.getMessage(), args));
        this.errorCode = errorCode;
    }

    @Override
    public BaseErrorCode getErrorCode() {
        return errorCode;
    }
}
