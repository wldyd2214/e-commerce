package com.hhplus.commerce.spring.presentation.order.dto.response;

import com.hhplus.commerce.spring.presentation.order.dto.OrderItemDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {

    @Schema(description = "주문 아이디", example = "1")
    private Long id;

    @Schema(description = "주문 사용자 아이디", example = "1")
    private Long userId;

    @Schema(description = "주문 목록 정보")
    private List<OrderItemDTO> orderItems;
}
