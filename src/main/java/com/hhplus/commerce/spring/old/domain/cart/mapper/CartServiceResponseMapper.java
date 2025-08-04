package com.hhplus.commerce.spring.old.domain.cart.mapper;

import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartItemInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartProductInfo;
import com.hhplus.commerce.spring.old.domain.cart.dto.common.CartUserInfo;
import com.hhplus.commerce.spring.old.domain.cart.model.Cart;
import com.hhplus.commerce.spring.old.domain.cart.model.CartItem;
import com.hhplus.commerce.spring.old.domain.cart.model.CartProduct;
import com.hhplus.commerce.spring.old.domain.cart.model.CartUser;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartServiceResponseMapper {

   default CartInfo toCartInfo(Cart cart) {

       List<CartItemInfo> cartItemInfos =
           cart.getCartItems().stream().map(this::toCartItemInfo).collect(Collectors.toList());

       return new CartInfo(cart.getId(), toCartUserInfo(cart.getUser()), cartItemInfos);
   }

   CartUserInfo toCartUserInfo(CartUser cartUser);

   default CartItemInfo toCartItemInfo(CartItem cartItem) {
       return new CartItemInfo(cartItem.getId(), toCartProductInfo(cartItem.getProduct()), cartItem.getCartItemProductCount());
   }

   CartProductInfo toCartProductInfo(CartProduct cartProduct);
}
