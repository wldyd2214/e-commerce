package com.hhplus.commerce.spring.presentation.cart.mapper;

import com.hhplus.commerce.spring.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.domain.cart.dto.common.CartItemInfo;
import com.hhplus.commerce.spring.presentation.cart.dto.CartResponse;
import com.hhplus.commerce.spring.presentation.cart.dto.CartResponse.AddCartItem;
import com.hhplus.commerce.spring.presentation.cart.dto.common.CartItemDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartResponseMapper {

    default CartResponse.AddCartItem toAddCartIem(CartInfo cartInfo) {

        List<CartItemDTO> cartItemDTOs =
            cartInfo.getCartItemInfos().stream().map(this::toCartItemDTO).collect(Collectors.toList());

        return new CartResponse.AddCartItem(cartInfo.getId(), cartItemDTOs);
    }

    default CartItemDTO toCartItemDTO(CartItemInfo cartItemInfo) {
        return new CartItemDTO(cartItemInfo.getProductInfo().getId(), cartItemInfo.getOrderCount());
    }
}
