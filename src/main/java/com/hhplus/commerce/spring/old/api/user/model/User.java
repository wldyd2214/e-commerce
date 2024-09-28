package com.hhplus.commerce.spring.old.api.user.model;

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
