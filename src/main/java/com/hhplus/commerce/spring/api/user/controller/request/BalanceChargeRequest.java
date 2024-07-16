package com.hhplus.commerce.spring.api.user.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceChargeRequest {

    @Schema(description = "충전 액수", example = "10000")
    @Positive(message = "충전 액수는 0보다 커야 합니다.")
    private int chargePoint;
}
