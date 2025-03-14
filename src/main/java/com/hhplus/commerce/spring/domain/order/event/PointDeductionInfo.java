package com.hhplus.commerce.spring.domain.order.event;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PointDeductionInfo {

    private Long userId;
    private BigDecimal deductPoint;
}
