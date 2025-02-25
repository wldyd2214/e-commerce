package com.hhplus.commerce.spring.presentation.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;

import com.hhplus.commerce.spring.presentation.order.dto.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {

    @Mapping(source = "orderItems", target = "orders")
    OrderFacadeRequest.Create toOrderCreate(OrderRequest request);
}
