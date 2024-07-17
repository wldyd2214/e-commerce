package com.hhplus.commerce.spring.api.cart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.api.cart.service.CartService;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    CartRepository cartRepository;
    @Mock
    CartItemRepository cartItemRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    CartService cartService;

    @DisplayName("장바구니 목록 조회에 성공한다.")
    @Test
    void getCartItems() {
        long userId = 1;
        String userName = "박지용";
        int userPoint = 10000;

        User user = createUser(userId, userName, userPoint);

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(user));

        Cart cart = createCart(user);
        given(cartRepository.findByUser(user))
                .willReturn(Optional.ofNullable(createCart(user)));

        given(cartItemRepository.findAllByCart(any()))
                .willReturn(List.of(createCartItem()));

        List<CartItem> cartItems = cartService.getCartItems(userId);

        assertThat(cartItems).isNotNull();
    }

    private User createUser(long userId, String userName, int userPoint) {
        return User.builder()
                   .id(userId)
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }

    private Cart createCart(User user) {
        return Cart.builder()
                   .user(user)
                   .build();
    }

    private CartItem createCartItem() {
        return CartItem.builder()
                       .cartItemProductCount(1)
                       .build();
    }

    @DisplayName("장바구니에 정보를 추가한다.")
    @Test
    void addCartItems() {
        long userId = 1;
        String userName = "박지용";
        int userPoint = 10000;

        User user = createUser(userId, userName, userPoint);

        given(userRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(user));

        given(cartRepository.findByUser(user))
                .willReturn(Optional.ofNullable(createCart(user)));

        given(productRepository.findById(anyLong()))
                .willReturn(Optional.ofNullable(Product.builder()
                                                       .productName("상품")
                                                       .build()));

        CartRegisterRequest request = CartRegisterRequest.builder()
                                                         .cartItems(List.of(
                                                                         CartItemRegister.builder()
                                                                                         .productId(1L)
                                                                                         .orderCount(1)
                                                                                         .build())).build();

        List<CartItem> cartItems = cartService.addCartItems(userId, request);

        assertThat(cartItems).isNotNull();
    }
}
