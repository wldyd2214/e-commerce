package com.hhplus.commerce.spring.api.controller.order.request;

import com.hhplus.commerce.spring.api.controller.order.dto.OrderPaymentDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaymentRequest {

    @Schema(description = "사용자 아이디", example = "1")
    @Positive
    private Long userId;

    @Schema(description = "주문 정보 목록")
    @NotNull
    @Valid
    private List<OrderPaymentDTO> orderItems;
}
