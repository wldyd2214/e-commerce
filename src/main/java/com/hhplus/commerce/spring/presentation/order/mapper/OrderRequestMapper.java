package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.presentation.order.dto.OrderPaymentDTO;
import com.hhplus.commerce.spring.presentation.order.dto.request.OrderRequestDTO;

import java.util.stream.Collectors;

public class OrderRequestMapper {

    public static OrderFacadeRequest toOrder(OrderRequestDTO request) {
        return OrderFacadeRequest.builder()
                                        .userId(request.getUserId())
                                        .orders(request.getOrderItems()
                                                        .stream()
                                                        .map(OrderRequestMapper::toOrderFacadeDTO)
                                                        .collect(Collectors.toList()))
                                        .build();
    }

    public static OrderFacadeDTO toOrderFacadeDTO(OrderPaymentDTO dto) {
        return OrderFacadeDTO.builder()
                             .productId(dto.getProductId())
                             .orderCount(dto.getOrderCount())
                             .build();
    }
}
