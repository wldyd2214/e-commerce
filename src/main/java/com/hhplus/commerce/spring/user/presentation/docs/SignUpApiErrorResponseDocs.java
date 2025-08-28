package com.hhplus.commerce.spring.user.presentation.docs;

import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.CustomBaseException;
import com.hhplus.commerce.spring.common.exception.CustomConflictException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ApiResponses({
    @ApiResponse(responseCode = "400", description = "잘못된 요청 파라미터",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomBadRequestException.class),
            examples = {
                @ExampleObject(
                    name = "잘못된 요청 파라미터",
                    value = "{\"code\":\"40000000\", \"message\":\"잘못된 요청 파라미터\", \"data\":null}"
                ),
            }
        )
    ),
    @ApiResponse(responseCode = "409", description = "이미 존재하는 회원",
        content = @Content(
            mediaType = "application/json",
            schema = @Schema(implementation = CustomConflictException.class),
            examples = {
                @ExampleObject(
                    name = "이미 사용중인 이메일",
                    value = "{\"code\":\"40900001\", \"message\":\"이미 사용중인 이메일입니다: jypark@gmail.com\", \"data\":null}"
                ),
            }
        )
    )
})
public @interface SignUpApiErrorResponseDocs {}
