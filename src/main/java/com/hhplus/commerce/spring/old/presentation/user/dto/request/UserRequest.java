package com.hhplus.commerce.spring.old.presentation.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PointCharge {

        @Schema(description = "충전할 금액", example = "10000")
        @NotNull(message = "충전 금액은 필수입니다.")
        @Positive(message = "충전 금액은 0보다 커야 합니다.")
        private BigDecimal chargePoint;
    }
}
