package com.hhplus.commerce.spring.api.cart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.old.api.cart.service.CartService;
import com.hhplus.commerce.spring.old.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.old.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.old.api.cart.model.Cart;
import com.hhplus.commerce.spring.old.api.cart.model.CartItem;
import com.hhplus.commerce.spring.old.api.cart.repository.CartItemRepository;
import com.hhplus.commerce.spring.old.api.cart.repository.CartRepository;
import com.hhplus.commerce.spring.old.api.cart.service.response.CartServiceRes;
import com.hhplus.commerce.spring.old.api.product.model.Product;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.old.api.user.model.User;
import com.hhplus.commerce.spring.old.api.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
// TODO: 어노테이션이 제거 되면 발생되는 에러에 대해서 공부해보기
@MockitoSettings(strictness = Strictness.LENIENT)
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

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));
        given(cartRepository.findByUser(user)).willReturn(Optional.ofNullable(createCart(user)));
        given(cartItemRepository.findAllByCart(any())).willReturn(List.of(createCartItem()));

        CartServiceRes result = cartService.getCart(userId);

        assertThat(result).isNotNull();
    }

    @DisplayName("장바구니에 정보를 추가한다.")
    @Test
    void addCartItems() {
        long userId = 1;
        String userName = "박지용";
        int userPoint = 10000;

        User user = createUser(userId, userName, userPoint);

        given(userRepository.findById(anyLong())).willReturn(Optional.ofNullable(user));

        given(cartRepository.findByUser(user)).willReturn(Optional.ofNullable(createCart(user)));

        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(Product.builder()
                                                                                           .productName("상품")
                                                                                           .build()));

        CartRegisterRequest request = CartRegisterRequest.builder()
                                                         .cartItems(List.of(
                                                                         CartItemRegister.builder()
                                                                                         .productId(1L)
                                                                                         .orderCount(1)
                                                                                         .build())).build();

        Cart cart = cartService.addCart(userId, request);

        assertThat(cart).isNotNull();
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
}
