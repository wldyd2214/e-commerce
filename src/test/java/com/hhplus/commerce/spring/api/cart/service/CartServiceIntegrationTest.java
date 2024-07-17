package com.hhplus.commerce.spring.api.cart.service;

import static com.hhplus.commerce.spring.api.order.model.type.OrderStatus.COMPLETED;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.spring.api.cart.infrastructure.database.CartItemJpaRepository;
import com.hhplus.commerce.spring.api.cart.infrastructure.database.CartJpaRepository;
import com.hhplus.commerce.spring.api.cart.model.Cart;
import com.hhplus.commerce.spring.api.cart.model.CartItem;
import com.hhplus.commerce.spring.api.cart.service.request.CartItemRegister;
import com.hhplus.commerce.spring.api.cart.service.request.CartRegisterRequest;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.api.user.infrastructure.database.UserJpaRepository;
import com.hhplus.commerce.spring.api.user.model.User;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class CartServiceIntegrationTest {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private CartJpaRepository cartJpaRepository;
    @Autowired
    private CartItemJpaRepository cartItemJpaRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    @DisplayName("유효하지 않은 사용자의 경우 장바구니 목록 조회에 실패한다.")
    @Test
    void invalidUserGetCartItems() {
        // given
        long userId = -1;

        // when // then
        assertThatThrownBy(() -> cartService.getCart(userId))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않은 사용자");
    }

    @DisplayName("장바구니가 비어있는 경우 목록 조회시 예외가 발생한다.")
    @Test
    void emptyCartItems() {
        // given
        String userName = "박지용";
        int userPoint = 10000;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        // when // then
        assertThatThrownBy(() -> cartService.getCart(saveUser.getId()))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("장바구니 목록 비어있음.");

        userJpaRepository.deleteById(saveUser.getId());
    }

    @DisplayName("장바구니 목록 조회에 성공한다.")
    @Test
    void getCartItems() {
        // given
        String userName = "박지용";
        int userPoint = 10000;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        Cart saveCart = Cart.create(saveUser);
        cartJpaRepository.save(saveCart);

        long productId = 1;
        Optional<Product> product = productRepository.findById(productId);

        int cartProductCount = 2;
        CartItem cartItem = CartItem.create(saveCart, product.get(), cartProductCount);
        cartItemJpaRepository.saveAll(List.of(cartItem));

        // when
        Cart cart = cartService.getCart(saveUser.getId());

        // then
        assertThat(cart.getCartItems()).hasSize(1)
                                   .extracting("id", "cartItemProductCount")
                                   .containsExactlyInAnyOrder(
                                       tuple(cartItem.getId(), cartItem.getCartItemProductCount())
                                   );

        userJpaRepository.deleteById(saveUser.getId());
        cartJpaRepository.deleteById(saveCart.getId());
        cartItemJpaRepository.deleteById(cartItem.getId());
    }

    @DisplayName("장바구니 정보를 추가한다.")
    @Test
    void addCartItems() {
        // given
        String userName = "박지용";
        int userPoint = 10000;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        long productId = 1;
        int orderCount = 1;
        CartItemRegister cartItemRegister = CartItemRegister.builder()
                                                            .productId(productId)
                                                            .orderCount(orderCount)
                                                            .build();

        CartRegisterRequest request = CartRegisterRequest.builder()
                                                         .cartItems(List.of(cartItemRegister))
                                                         .build();

        // when
        //List<CartItem> cartItems = cartService.addCartItems(saveUser.getId(), request);

        // then
//        assertThat(cartItems).hasSize(1)
//                             .extracting("id", "cartItemProductCount")
//                             .containsExactlyInAnyOrder(
//                                 tuple(cartItem.getId(), cartItem.getCartItemProductCount())
//                             );
    }

    private User createUser(String userName, int userPoint) {
        return User.builder()
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }
}
