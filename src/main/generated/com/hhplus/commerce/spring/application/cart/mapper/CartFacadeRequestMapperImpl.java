package com.hhplus.commerce.spring.application.cart.mapper;

import com.hhplus.commerce.spring.old.application.cart.dto.common.CartItemFacadeDTO;
import com.hhplus.commerce.spring.old.application.cart.mapper.CartFacadeRequestMapper;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class CartFacadeRequestMapperImpl implements CartFacadeRequestMapper {

    @Override
    public CartCommand.CartItem toCartItemFacadeDTO(CartItemFacadeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Integer cartQuantity = null;
        Long productId = null;

        cartQuantity = dto.getOrderCount();
        productId = dto.getProductId();

        CartCommand.CartItem cartItem = new CartCommand.CartItem( productId, cartQuantity );

        return cartItem;
    }
}
