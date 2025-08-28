package com.hhplus.commerce.spring.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class UserTokenInfo {

    @Schema(description = "인증 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqeXBhcmtAZ21haWwuY29tIiwidXNlciI6eyJrZXkiOjEsImVtYWlsIjoianlwYXJrQGdtYWlsLmNvbSIsIm5hbWUiOiLrsJXsp4DsmqkiLCJwYXNzd29yZCI6IiQyYSQxMCRaNXc3SlluellzU01yWWVmeFFiUmsuOEtOQ3lzTXdjc2JOaDM0SDg4STd2c200OFhNeWMyeSIsInVzZXJuYW1lIjoianlwYXJrQGdtYWlsLmNvbSIsImF1dGhvcml0aWVzIjpbXSwiZW5hYmxlZCI6dHJ1ZSwiYWNjb3VudE5vbkV4cGlyZWQiOnRydWUsImFjY291bnROb25Mb2NrZWQiOnRydWUsImNyZWRlbnRpYWxzTm9uRXhwaXJlZCI6dHJ1ZX0sImlhdCI6MTc1NjM2ODQ4MCwiZXhwIjoxNzU2NDExNjgwfQ.rBb1beYXHkQZes2LZUGp4t3BRt4K2YuKXS5XYpreV2o")
    @NotNull
    private String accessToken;

    @Schema(description = "인증 토큰 만료 시간(초)", example = "43200")
    @NotNull
    private Long expireSeconds;

    public static UserTokenInfo of(String accessToken, Long expireSeconds) {
        return UserTokenInfo.builder()
                .accessToken(accessToken)
                .expireSeconds(expireSeconds)
                .build();
    }
}
