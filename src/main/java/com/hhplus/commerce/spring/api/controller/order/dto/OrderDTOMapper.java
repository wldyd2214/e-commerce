package com.hhplus.commerce.spring.api.controller.order.dto;

import com.hhplus.commerce.spring.api.controller.order.response.OrderPaymentResponse;
import com.hhplus.commerce.spring.api.controller.product.dto.ProductDTOMapper;

import java.util.List;

public class OrderDTOMapper {

    public static OrderPaymentResponse createDummyOrderPaymentResponse() {
        return OrderPaymentResponse.builder()
                                   .userId(1L)
                                   .balanceAmount(10000)
                                   .orderItems(
                                           List.of(
                                                   createDummyOrderDTO(1L, "아더에러 바지"),
                                                   createDummyOrderDTO(2L, "아더에러 티셔츠")
                                           )
                                   )
                                   .build();
    }

    public static OrderDTO createDummyOrderDTO(Long orderId, String productName) {
        return OrderDTO.builder()
                       .orderId(orderId)
                       .sellPrice(2600000)
                       .orderCount(1)
                       .product(ProductDTOMapper.createProductDTO(orderId, productName, 2600000, 10))
                       .build();
    }
}
