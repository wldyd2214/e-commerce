package com.hhplus.commerce.spring.api.common.presentation;

import com.hhplus.commerce.spring.api.common.presentation.exception.CustomConflictException;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.api.common.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ApiResponse<Object> bindException(BindException e) {
        return ApiResponse.of(
                HttpStatus.BAD_REQUEST,
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<Object> IllegalArgumentException(IllegalArgumentException e) {
        return ApiResponse.of(
            HttpStatus.BAD_REQUEST,
            e.getMessage(),
            null
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CustomConflictException.class)
    public ApiResponse<Object> ConflictException(CustomConflictException e) {
        return ApiResponse.of(
                HttpStatus.CONFLICT,
                e.getMessage(),
                null
        );
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(CustomBadGateWayException.class)
    public ApiResponse<Object> BadGateWayException(CustomBadGateWayException e) {
        return ApiResponse.of(
                HttpStatus.BAD_GATEWAY,
                e.getMessage(),
                null
        );
    }
}
