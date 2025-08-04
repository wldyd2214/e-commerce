package com.hhplus.commerce.spring.presentation.cart.mapper;

import com.hhplus.commerce.spring.old.application.cart.dto.CartFacadeRequest;
import com.hhplus.commerce.spring.old.application.cart.dto.common.CartItemFacadeDTO;
import com.hhplus.commerce.spring.old.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.old.presentation.cart.dto.CartRequest;
import com.hhplus.commerce.spring.old.presentation.cart.dto.common.CartItemDTO;
import com.hhplus.commerce.spring.old.presentation.cart.mapper.CartRequestMapper;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class CartRequestMapperImpl implements CartRequestMapper {

    @Override
    public CartFacadeRequest.AddItem toFacadeAddItem(Long userId, CartRequest.AddItem addItem) {
        if ( userId == null && addItem == null ) {
            return null;
        }

        List<CartItemFacadeDTO> cartItems = null;
        if ( addItem != null ) {
            cartItems = cartItemDTOListToCartItemFacadeDTOList( addItem.getCartItems() );
        }
        Long userId1 = null;
        userId1 = userId;

        CartFacadeRequest.AddItem addItem1 = new CartFacadeRequest.AddItem( userId1, cartItems );

        return addItem1;
    }

    @Override
    public CartCommand.RemoveCartItem toRemoveCartItem(Long userId, List<Long> cartItemIds) {
        if ( userId == null && cartItemIds == null ) {
            return null;
        }

        Long userId1 = null;
        userId1 = userId;
        List<Long> cartItemIds1 = null;
        List<Long> list = cartItemIds;
        if ( list != null ) {
            cartItemIds1 = new ArrayList<Long>( list );
        }

        CartCommand.RemoveCartItem removeCartItem = new CartCommand.RemoveCartItem( userId1, cartItemIds1 );

        return removeCartItem;
    }

    protected CartItemFacadeDTO cartItemDTOToCartItemFacadeDTO(CartItemDTO cartItemDTO) {
        if ( cartItemDTO == null ) {
            return null;
        }

        Long productId = null;
        Integer orderCount = null;

        productId = cartItemDTO.getProductId();
        orderCount = cartItemDTO.getOrderCount();

        CartItemFacadeDTO cartItemFacadeDTO = new CartItemFacadeDTO( productId, orderCount );

        return cartItemFacadeDTO;
    }

    protected List<CartItemFacadeDTO> cartItemDTOListToCartItemFacadeDTOList(List<CartItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemFacadeDTO> list1 = new ArrayList<CartItemFacadeDTO>( list.size() );
        for ( CartItemDTO cartItemDTO : list ) {
            list1.add( cartItemDTOToCartItemFacadeDTO( cartItemDTO ) );
        }

        return list1;
    }
}
