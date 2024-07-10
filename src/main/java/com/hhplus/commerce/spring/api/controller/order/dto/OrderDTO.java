package com.hhplus.commerce.spring.api.controller.order.dto;

import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @Schema(description = "주문 아이디", example = "1")
    private Long orderId;
    @Schema(description = "주문 금액", example = "100000")
    private Integer sellPrice;
    @Schema(description = "주문 갯수", example = "2")
    private Integer orderCount;
    @Schema(description = "주문 상품 정보")
    private ProductDTO product;
}
