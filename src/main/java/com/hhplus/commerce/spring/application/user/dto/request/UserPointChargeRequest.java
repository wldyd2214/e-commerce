package com.hhplus.commerce.spring.application.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserPointChargeRequest {

    private Long userId;
    private Integer point;
}
