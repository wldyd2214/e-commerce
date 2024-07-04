package com.hhplus.commerce.spring.api.controller.order.dto;

import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    private Long orderId;
    private Integer sellPrice;
    private Integer orderCount;
    private ProductDTO product;
}
