package com.hhplus.commerce.spring.old.presentation.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.old.presentation.order.dto.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    @Mapping(source = "orders", target = "orderItems")
    OrderResponse toOrderCreate(OrderFacadeResponse.Create create);
}
