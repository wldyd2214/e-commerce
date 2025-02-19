package com.hhplus.commerce.spring.presentation.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private BigDecimal point;
}
