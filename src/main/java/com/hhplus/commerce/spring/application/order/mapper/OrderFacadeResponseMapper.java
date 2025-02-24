package com.hhplus.commerce.spring.application.order.mapper;

import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderFacadeResponseMapper {

    OrderFacadeResponse.Create toCreate(OrderInfo orderInfo);
}
