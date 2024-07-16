package com.hhplus.commerce.spring.api.service.cart;

import com.hhplus.commerce.spring.api.service.cart.request.CartItemRegister;
import com.hhplus.commerce.spring.api.service.cart.request.CartRegisterRequest;
import com.hhplus.commerce.spring.model.Cart;
import com.hhplus.commerce.spring.model.CartItem;
import com.hhplus.commerce.spring.model.Product;
import com.hhplus.commerce.spring.model.User;
import com.hhplus.commerce.spring.repository.CartItemRepository;
import com.hhplus.commerce.spring.repository.CartRepository;
import com.hhplus.commerce.spring.repository.ProductRepository;
import com.hhplus.commerce.spring.repository.UserRepository;
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

    public List<CartItem> getCartItems(Long userId) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        Cart cart = cartRepository.findByUser(user)
                                  .orElseThrow(() -> new IllegalArgumentException("장바구니 목록 비어있음."));

        List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);

        return cartItems;
    }

    public List<CartItem> addCartItems(Long userId, CartRegisterRequest cartRegisterRequest) {

        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        Cart cart = cartRepository.findByUser(user)
                                  .orElse(Cart.create(user));

        cartRepository.save(cart);

        List<CartItem> cartItems = new ArrayList<>();

        for (CartItemRegister cartItemRegister : cartRegisterRequest.getCartItems()) {
            Product product = productRepository.findById(cartItemRegister.getProductId())
                                               .orElseThrow(() -> new IllegalArgumentException("미존재 상품 정보"));

            CartItem cartItem = CartItem.create(cart, product, cartItemRegister.getOrderCount());
            cartItemRepository.save(cartItem);

            cartItems.add(cartItem);
        }

        return cartItems;
    }

    @Transactional
    public void removeCartItems(List<Long> cartItemKeys) {
        cartItemRepository.removeAllByIdIn(cartItemKeys);
    }
}
