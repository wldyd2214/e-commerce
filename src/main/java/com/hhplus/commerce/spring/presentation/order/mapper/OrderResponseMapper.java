package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.dto.response.OrderItemFacadeDTO;
import com.hhplus.commerce.spring.presentation.order.dto.OrderItemDTO;
import com.hhplus.commerce.spring.presentation.order.dto.response.OrderResponseDTO;
import java.util.stream.Collectors;

public class OrderResponseMapper {

    public static OrderResponseDTO toOrder(OrderFacadeResponse order) {
        return OrderResponseDTO.builder()
                               .id(order.getId())
                               .userId(order.getUserId())
                               .orderItems(
                                   order.getOrders()
                                        .stream()
                                        .map(item -> toOrderItemDTO(item))
                                        .collect(Collectors.toList()))
                               .build();
    }

    public static OrderItemDTO toOrderItemDTO(OrderItemFacadeDTO item) {
        return OrderItemDTO.builder()
                           .id(item.getId())
                           .productName(item.getProductName())
                           .productPrice(item.getProductPrice())
                           .count(item.getOrderCount())
                           .build();
    }

}
