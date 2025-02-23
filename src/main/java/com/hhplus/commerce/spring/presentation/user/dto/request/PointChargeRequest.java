package com.hhplus.commerce.spring.presentation.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PointChargeRequest {

    @Schema(description = "충전 액수", example = "10000")
    private BigDecimal chargePoint;
}
