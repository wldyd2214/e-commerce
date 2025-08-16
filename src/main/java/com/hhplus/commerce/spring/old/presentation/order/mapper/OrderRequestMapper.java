package com.hhplus.commerce.spring.old.presentation.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.request.OrderFacadeRequest.Create;
import com.hhplus.commerce.spring.old.presentation.order.dto.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderRequestMapper {

    @Mapping(source = "orderItems", target = "orders")
    Create toOrderCreate(OrderRequest request);
}
