package com.hhplus.commerce.spring.presentation.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PointCharge {

        @Schema(description = "충전 액수", example = "10000")
        private BigDecimal chargePoint;
    }
}
