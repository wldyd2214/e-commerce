package com.hhplus.commerce.spring.presentation.cart.mapper;

import com.hhplus.commerce.spring.application.cart.dto.CartFacadeRequest;
import com.hhplus.commerce.spring.presentation.cart.dto.CartRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartRequestMapper {

    CartFacadeRequest.AddItem toFacadeAddItem(Long userId, CartRequest.AddItem addItem);
}
