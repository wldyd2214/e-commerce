package com.hhplus.commerce.spring.api.cart.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.spring.old.api.cart.infrastructure.database.CartItemJpaRepository;
import com.hhplus.commerce.spring.old.api.cart.infrastructure.database.CartJpaRepository;
import com.hhplus.commerce.spring.old.api.cart.service.CartService;
import com.hhplus.commerce.spring.domain.product.repository.ProductQueryRepository;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
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
    @Autowired
    private ProductQueryRepository productQueryRepository;

//    @DisplayName("유효하지 않은 사용자의 경우 장바구니 목록 조회에 실패한다.")
//    @Test
//    void invalidUserGetCartItems() {
//        // given
//        long userId = -1;
//
//        // when // then
//        assertThatThrownBy(() -> cartService.getCart(userId))
//            .isInstanceOf(CustomBadRequestException.class)
//            .hasMessage(USER_BAD_REQUEST.getMessage());
//    }
//
//    @DisplayName("장바구니가 비어있는 경우 목록 조회시 예외가 발생한다.")
//    @Test
//    void emptyCartItems() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        // when // then
//        assertThatThrownBy(() -> cartService.getCart(saveUser.getId()))
//            .isInstanceOf(CustomBadRequestException.class)
//            .hasMessage(CART_BAD_REQUEST.getMessage());
//
//        userJpaRepository.deleteById(saveUser.getId());
//    }
//
//    @DisplayName("장바구니 목록 조회에 성공한다.")
//    @Test
//    void getCartItems() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        Cart saveCart = Cart.create(saveUser);
//        cartJpaRepository.save(saveCart);
//
//        long productId = 1;
//        Optional<Product> product = productRepository.findById(productId);
//
//        int cartProductCount = 2;
//        CartItem cartItem = CartItem.create(saveCart, product.get(), cartProductCount);
//        cartItemJpaRepository.saveAll(List.of(cartItem));
//
//        // when
//        CartServiceRes cart = cartService.getCart(saveUser.getId());
//
//        // then
//        assertThat(cart.getCartItems()).hasSize(1)
//                                   .extracting("id", "addProductCount")
//                                   .containsExactlyInAnyOrder(
//                                       tuple(cartItem.getId(), cartItem.getCartItemProductCount())
//                                   );
//
//        userJpaRepository.deleteById(saveUser.getId());
//        cartJpaRepository.deleteById(saveCart.getId());
//        cartItemJpaRepository.deleteById(cartItem.getId());
//    }
//
//    @DisplayName("유효하지 않은 사용자의 경우 장바구니 정보 추가에 실패한다.")
//    @Test
//    void invalidUserAddCart() {
//        // given
//        long userId = -1;
//
//        long productId = 1;
//        int orderCount = 1;
//        CartItemRegister cartItemRegister = CartItemRegister.builder()
//                                                            .productId(productId)
//                                                            .orderCount(orderCount)
//                                                            .build();
//
//        CartRegisterRequest request = CartRegisterRequest.builder()
//                                                         .cartItems(List.of(cartItemRegister))
//                                                         .build();
//
//        // when // then
//        assertThatThrownBy(() -> cartService.addCart(userId, request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(USER_BAD_REQUEST.getMessage());
//    }
//
//    @DisplayName("미존재 상품 정보의 경우 장바구니 정보 추가에 실패한다.")
//    @Test
//    void invalidProductAddCart() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000;
//
//        long productId = -1;
//        int orderCount = 1;
//        CartItemRegister cartItemRegister = CartItemRegister.builder()
//                                                            .productId(productId)
//                                                            .orderCount(orderCount)
//                                                            .build();
//
//        CartRegisterRequest request = CartRegisterRequest.builder()
//                                                         .cartItems(List.of(cartItemRegister))
//                                                         .build();
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        // then
//        assertThatThrownBy(() -> cartService.addCart(saveUser.getId(), request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(PRODUCT_BAD_REQUEST.getMessage());
//
//        userJpaRepository.deleteById(saveUser.getId());
//    }
//
//    @DisplayName("장바구니 정보를 추가한다.")
//    @Test
//    void addCartItems() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        long productId = 1;
//        int orderCount = 1;
//        CartItemRegister cartItemRegister = CartItemRegister.builder()
//                                                            .productId(productId)
//                                                            .orderCount(orderCount)
//                                                            .build();
//
//        CartRegisterRequest request = CartRegisterRequest.builder()
//                                                         .cartItems(List.of(cartItemRegister))
//                                                         .build();
//
//        // when
//        Cart cart = cartService.addCart(saveUser.getId(), request);
//
//        // then
//        assertThat(cart.getCartItems()).hasSize(1)
//                                       .extracting("id", "cartItemProductCount")
//                                       .containsExactlyInAnyOrder(
//                                               tuple(cart.getCartItems().get(0).getId(), cart.getCartItems().get(0).getCartItemProductCount())
//                                       );
//
//        userJpaRepository.deleteById(saveUser.getId());
//        cartItemJpaRepository.deleteAllByIdInBatch(cart.getCartItems().stream().map(i -> i.getId()).collect(Collectors.toList()));
//        cartJpaRepository.deleteById(cart.getId());
//    }
//
//    @DisplayName("장바구니 정보를 삭제한다.")
//    @Test
//    void removeCartItems() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        Product saveProduct = createProduct("테스트 상품");
//        productJpaRepository.save(saveProduct);
//
//        CartItemRegister cartItemRegister1 = cartItemRegister(saveProduct.getId(), 1);
//        CartItemRegister cartItemRegister2 = cartItemRegister(saveProduct.getId(), 1);
//
//        CartRegisterRequest request = CartRegisterRequest.builder()
//                                                         .cartItems(List.of(cartItemRegister1, cartItemRegister2))
//                                                         .build();
//
//        // when
//        Cart cart = cartService.addCart(saveUser.getId(), request);
//
//        List<Long> cartItemKeys = List.of(cart.getCartItems().get(0).getId());
//        CartServiceRes cartServiceRes = cartService.removeCartItems(saveUser.getId(), cartItemKeys);
//
//        // then
//        assertThat(cartServiceRes.getCartItems())
//                .hasSize(1)
//                .extracting("id", "addProductCount")
//                .containsExactlyInAnyOrder(
//                        tuple(cart.getCartItems().get(1).getId(), cart.getCartItems().get(1).getCartItemProductCount())
//                );
//    }
//
//    private User createUser(String userName, int userPoint) {
//        return User.builder()
//                   .userName(userName)
//                   .userPoint(userPoint)
//                   .build();
//    }
//
//    private CartItemRegister cartItemRegister(Long productId, int orderCount) {
//        return CartItemRegister.builder()
//                               .productId(productId)
//                               .orderCount(orderCount)
//                               .build();
//    }
//
//    private Product createProduct(String name) {
//        return Product.builder()
//                      .productName(String.format("%s 제품 이름", name))
//                      .productDesc(String.format("%s 제품 내용", name))
//                      .productPrice(100000)
//                      .productCount(50)
//                      .build();
//    }
}
