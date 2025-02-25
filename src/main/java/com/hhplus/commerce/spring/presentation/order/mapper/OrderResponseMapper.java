package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.dto.response.OrderItemFacadeDTO;
import com.hhplus.commerce.spring.presentation.order.dto.OrderItemDTO;
import com.hhplus.commerce.spring.presentation.order.dto.response.OrderResponse;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderResponseMapper {

    @Mapping(source = "orders", target = "orderItems")
    OrderResponse toOrderCreate(OrderFacadeResponse.Create create);
}
