package com.hhplus.commerce.spring.old.presentation.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private BigDecimal point;
}
