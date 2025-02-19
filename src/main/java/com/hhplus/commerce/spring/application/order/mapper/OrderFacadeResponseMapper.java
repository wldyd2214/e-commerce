package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.dto.response.OrderItemFacadeDTO;
import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import java.util.stream.Collectors;

public class OrderFacadeResponseMapper {

    public static OrderFacadeResponse toOrder(OrderInfo info) {
        return OrderFacadeResponse.builder()
                                  .id(info.getId())
                                  .userId(info.getUserId())
                                  .orders(info.getOrders()
                                              .stream()
                                              .map(item -> toOrderItem(item))
                                              .collect(Collectors.toList()))
                                  .build();
    }

    public static OrderItemFacadeDTO toOrderItem(OrderInfo.OrderItemInfo info) {
        return OrderItemFacadeDTO.builder()
                                 .id(info.getId())
                                 .productName(info.getProductName())
                                 .productPrice(info.getProductPrice())
                                 .orderCount(info.getOrderCount())
                                 .build();
    }
}
