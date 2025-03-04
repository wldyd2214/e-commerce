package com.hhplus.commerce.spring.domain.cart.dto.response;

import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.model.CartItem;
import com.hhplus.commerce.spring.domain.product.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartServiceRes {

    private Long id;

    private List<CartItemServiceRes> cartItems;

    public static CartServiceRes toCartServiceRes(Cart cart) {
        return CartServiceRes.builder()
                             .id(cart.getId())
                             .cartItems(cart.getCartItems().stream().map(CartServiceRes::toCartItemServiceRes).collect(Collectors.toList()))
                             .build();
    }

    public static CartItemServiceRes toCartItemServiceRes(CartItem cartItem) {
        return CartItemServiceRes.builder()
                                 .id(cartItem.getId())
//                                 .product(toCartItemProductRes(cartItem.getProduct()))
                                 .addProductCount(cartItem.getCartItemProductCount())
                                 .build();
    }

    public static CartItemProductRes toCartItemProductRes(Product product) {
        return CartItemProductRes.builder()
                                 .id(product.getId())
                                 .name(product.getName())
                                 .desc(product.getDesc())
                                 .price(product.getPrice())
                                 .count(product.getCount())
                                 .build();
    }
}
