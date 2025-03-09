package com.hhplus.commerce.spring.presentation.cart.mapper;

import com.hhplus.commerce.spring.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.domain.cart.dto.common.CartItemInfo;
import com.hhplus.commerce.spring.presentation.cart.dto.CartResponse;
import com.hhplus.commerce.spring.presentation.cart.dto.CartResponse.Cart;
import com.hhplus.commerce.spring.presentation.cart.dto.common.CartItemDetailDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartResponseMapper {

    default CartResponse.AddCartItem toAddCartIem(CartInfo cartInfo) {

        List<CartItemDetailDTO> cartItemDTOs =
            cartInfo.getCartItemInfos().stream().map(this::toCartItemDTO).collect(Collectors.toList());

        return new CartResponse.AddCartItem(cartInfo.getId(), cartItemDTOs);
    }

    default CartResponse.Cart toCart(CartInfo cartInfo) {

        List<CartItemDetailDTO> cartItemDTOs =
            cartInfo.getCartItemInfos().stream().map(this::toCartItemDTO).collect(Collectors.toList());

        return new CartResponse.Cart(cartInfo.getId(), cartItemDTOs);
    }

    default CartItemDetailDTO toCartItemDTO(CartItemInfo cartItemInfo) {
        return new CartItemDetailDTO(
            cartItemInfo.getId(),
            cartItemInfo.getProductInfo().getId(),
            cartItemInfo.getProductInfo().getName(),
            cartItemInfo.getOrderCount());
    }
}
