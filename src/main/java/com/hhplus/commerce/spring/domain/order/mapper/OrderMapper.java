package com.hhplus.commerce.spring.domain.order.mapper;

import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.domain.order.model.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderInfo toOrderInfo(Order order);

//    public static OrderInfo toOrderInfo(Order order) {
//        return OrderInfo.builder()
////                        .id(order.getId())
////                        .userId(order.getUser().getId())
////                        .orders(order.getOrderItem()
////                                     .stream()
////                                     .map(item -> toOrderItemInfo(item))
////                                     .collect(Collectors.toList()))
//                        .build();
//    }

//    public static OrderItemInfo toOrderItemInfo(OrderItem orderItem) {
//        return OrderInfo.OrderItemInfo
//            .builder()
//            .id(orderItem.getId())
////            .productName(orderItem.getOrderProductName())
////            .productPrice(orderItem.getOrderProductPrice())
////            .orderCount(orderItem.getOrderProductCount())
//            .build();
//    }
}
