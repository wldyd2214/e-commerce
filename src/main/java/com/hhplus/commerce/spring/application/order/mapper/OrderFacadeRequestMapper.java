package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand;
import java.util.stream.Collectors;

public class OrderFacadeRequestMapper {

    public static OrderCommand.Order toOrder(OrderFacadeRequest request) {
        return OrderCommand.Order.builder()
                                 .userId(request.getUserId())
                                 .orders(request.getOrders()
                                                .stream()
                                                .map(order -> toOrderItem(order))
                                                .collect(Collectors.toList()))
                                 .build();
    }

    public static OrderCommand.Order.OrderItem toOrderItem(OrderFacadeDTO request) {
        return OrderCommand.Order.OrderItem
            .builder()
            .productId(request.getProductId())
            .orderCount(request.getOrderCount())
            .build();
    }
}
