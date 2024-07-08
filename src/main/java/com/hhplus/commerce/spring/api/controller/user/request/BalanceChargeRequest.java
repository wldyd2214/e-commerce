package com.hhplus.commerce.spring.api.controller.user.request;

import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BalanceChargeRequest {

    @Positive(message = "충전 액수는 0보다 커야 합니다.")
    private int chargePoint;
}
