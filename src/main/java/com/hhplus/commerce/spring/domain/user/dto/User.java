package com.hhplus.commerce.spring.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class User {
    private Long id;
    private String name;
    private BigDecimal point;
}
