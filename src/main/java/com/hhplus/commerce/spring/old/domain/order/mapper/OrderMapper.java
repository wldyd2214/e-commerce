package com.hhplus.commerce.spring.old.domain.order.mapper;

import com.hhplus.commerce.spring.old.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.old.domain.order.model.Order;
import com.hhplus.commerce.spring.old.domain.order.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "orderItems", target = "orderItems", qualifiedByName = "toOrderItemInfo")
    OrderInfo toOrderInfo(Order order);

    @Named("toOrderItemInfo")
    @Mapping(source = "orderProductName", target = "productName")
    @Mapping(source = "orderProductPrice", target = "productPrice")
    @Mapping(source = "orderProductCount", target = "orderCount")
    OrderInfo.OrderItemInfo toOrderItemInfo(OrderItem orderItem);
}
