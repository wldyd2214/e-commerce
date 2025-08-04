package com.hhplus.commerce.spring.old.application.order.mapper;

import com.hhplus.commerce.spring.old.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.old.domain.order.dto.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderFacadeResponseMapper {

    @Mapping(source = "orderItems", target = "orders")
    OrderFacadeResponse.Create toCreate(OrderInfo orderInfo);
}
