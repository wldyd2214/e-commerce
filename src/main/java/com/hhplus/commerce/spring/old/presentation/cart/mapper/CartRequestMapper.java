package com.hhplus.commerce.spring.old.presentation.cart.mapper;

import com.hhplus.commerce.spring.old.application.cart.dto.CartFacadeRequest;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.old.presentation.cart.dto.CartRequest;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartRequestMapper {

    CartFacadeRequest.AddItem toFacadeAddItem(Long userId, CartRequest.AddItem addItem);

    CartCommand.RemoveCartItem toRemoveCartItem(Long userId, List<Long> cartItemIds);
}
