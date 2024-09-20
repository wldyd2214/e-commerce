package com.hhplus.commerce.spring.presentation.user.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointChargeRequestDTO {

    @Schema(description = "충전 액수", example = "10000")
    @Positive(message = "충전 액수는 0보다 커야 합니다.")
    private int chargePoint;
}
