package com.hhplus.commerce.spring.domain.cart.mapper;

import com.hhplus.commerce.spring.domain.cart.dto.CartInfo;
import com.hhplus.commerce.spring.presentation.cart.dto.response.CartResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartResponseMapper {

    CartResponse.CartInfo toCartInfo(CartInfo cart);
}
