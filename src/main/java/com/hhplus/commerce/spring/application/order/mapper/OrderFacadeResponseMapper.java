package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderFacadeResponseMapper {

    @Mapping(source = "orderItems", target = "orders")
    OrderFacadeResponse.Create toCreate(OrderInfo orderInfo);
}
