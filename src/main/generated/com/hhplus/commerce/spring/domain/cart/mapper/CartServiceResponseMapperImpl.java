package com.hhplus.commerce.spring.domain.cart.mapper;

import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartProductInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartUserInfo;
import com.hhplus.commerce.spring.old.domain.cart.mapper.CartServiceResponseMapper;
import com.hhplus.commerce.spring.old.domain.cart.model.CartProduct;
import com.hhplus.commerce.spring.old.domain.cart.model.CartUser;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-13T21:03:19+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class CartServiceResponseMapperImpl implements CartServiceResponseMapper {

    @Override
    public CartUserInfo toCartUserInfo(CartUser cartUser) {
        if ( cartUser == null ) {
            return null;
        }

        Long id = null;

        id = cartUser.getId();

        String name = null;
        BigDecimal point = null;

        CartUserInfo cartUserInfo = new CartUserInfo( id, name, point );

        return cartUserInfo;
    }

    @Override
    public CartProductInfo toCartProductInfo(CartProduct cartProduct) {
        if ( cartProduct == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer price = null;

        id = cartProduct.getId();
        name = cartProduct.getName();
        price = cartProduct.getPrice();

        CartProductInfo cartProductInfo = new CartProductInfo( id, name, price );

        return cartProductInfo;
    }
}
