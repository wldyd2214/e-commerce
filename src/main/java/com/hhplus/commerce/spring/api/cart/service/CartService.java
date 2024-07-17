package com.hhplus.commerce.spring.api.cart.service;

import com.hhplus.commerce.spring.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.api.cart.model.Cart;
import com.hhplus.commerce.spring.api.cart.model.CartItem;
import com.hhplus.commerce.spring.api.cart.repository.CartItemRepository;
import com.hhplus.commerce.spring.api.cart.repository.CartRepository;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Cart getCart(Long userId) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        return cartRepository.findByUser(user)
                             .orElseThrow(() -> new IllegalArgumentException("장바구니 목록 비어있음."));
    }

    public Cart addCart(Long userId, CartRegisterRequest cartRegisterRequest) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        Cart cart = cartRepository.findByUser(user).orElse(Cart.create(user));
        cartRepository.save(cart);

        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemRegister cartItemRegister : cartRegisterRequest.getCartItems()) {
            Product product = productRepository.findById(cartItemRegister.getProductId())
                                               .orElseThrow(() -> new IllegalArgumentException("미존재 상품 정보"));

            CartItem cartItem = CartItem.create(cart, product, cartItemRegister.getOrderCount());
            cartItemRepository.save(cartItem);

            cartItems.add(cartItem);
        }

        cart.getCartItems().addAll(cartItems);
        return cart;
    }

    @Transactional
    public void removeCartItems(List<Long> cartItemKeys) {
        cartItemRepository.removeAllByIdIn(cartItemKeys);
    }
}
