package com.hhplus.commerce.spring.presentation.user.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private BigDecimal point;
}
