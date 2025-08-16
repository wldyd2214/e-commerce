package com.hhplus.commerce.spring.old.application.cart.mapper;

import com.hhplus.commerce.spring.old.application.cart.dto.common.CartItemFacadeDTO;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.user.dto.UserInfo;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartFacadeRequestMapper {

    default List<Long> toProductIds(List<CartItemFacadeDTO> cartItems) {
        return cartItems.stream()
                        .map(CartItemFacadeDTO::getProductId)
                        .collect(Collectors.toList());
    }

    default CartCommand.AddItem toCartCommandAddItem(UserInfo userInfo, List<ProductInfo> productInfos,
        List<CartItemFacadeDTO> cartItems) {

        List<CartCommand.CartItem> cartItemInfos =
            cartItems.stream().map(this::toCartItemFacadeDTO).collect(Collectors.toList());

        return new CartCommand.AddItem(userInfo, productInfos, cartItemInfos);
    }

    @Mapping(source = "orderCount", target = "cartQuantity")
    CartCommand.CartItem toCartItemFacadeDTO(CartItemFacadeDTO dto);
}
