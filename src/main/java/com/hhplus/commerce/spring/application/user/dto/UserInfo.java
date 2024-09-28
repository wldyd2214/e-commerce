package com.hhplus.commerce.spring.application.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UserInfo {
    private Long id;
    private String name;
    private BigDecimal point;
}
