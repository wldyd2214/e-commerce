package com.hhplus.commerce.spring.old.domain.user.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInfo {
    private Long id;
    private String name;
    private BigDecimal point;
}
