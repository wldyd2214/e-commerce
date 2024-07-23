package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.order.service.request.CreateOrderServiceRequest;
import com.hhplus.commerce.spring.api.order.service.request.OrderServiceRequest;
import com.hhplus.commerce.spring.api.product.infrastructure.database.ProductJpaRepository;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.user.infrastructure.client.PaymentSystemClient;
import com.hhplus.commerce.spring.api.user.infrastructure.database.UserJpaRepository;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import static com.hhplus.commerce.spring.api.order.model.type.OrderStatus.COMPLETED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
public class OrderServiceIntegrationTest {
    @MockBean
    private PaymentSystemClient paymentSystemClient;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private OrderService orderService;

    @DisplayName("유효하지 않은 사용자의 경우 주문에 실패한다.")
    @Test
    void invalidUserOrderPayment() {
        // given
        long userId = -1;
        long productId = 1;
        int orderCount = 1;

        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, order);

        // when // then
        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않은 사용자");
    }

    @DisplayName("사용자의 잔액이 부족한 경우 주문에 실패한다.")
    @Test
    void lockOfUserPointOrderPayment() {
        // given
        String userName = "잔액 부족 사용자";
        int userPoint = 0;

        long productId = 1;
        int orderCount = 1;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);

        // when // then
        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("사용자 잔액 부족");

        userJpaRepository.deleteById(saveUser.getId());
    }

    @DisplayName("상품 재고가 부족한 경우 주문에 실패한다.")
    @Test
    void lackOfStockOrderPayment() {
        // given
        String userName = "박지용";
        int userPoint = 10000000;

        Product saveOutOfStockProduct = createOutOfStockProduct();
        productJpaRepository.save(saveOutOfStockProduct);

        long productId = saveOutOfStockProduct.getId();
        int orderCount = 5;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);

        // when // then
        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고가 부족한 상품이 있습니다.");

        productJpaRepository.deleteById(productId);
        userJpaRepository.deleteById(saveUser.getId());
    }

    @DisplayName("결제가 실패하는 경우 상품 주문에 실패한다.")
    @Test
    void orderPaymentFail() {
        // given
        String userName = "박지용";
        int userPoint = 10000000;

        Product saveOutOfStockProduct = createProduct();
        productJpaRepository.save(saveOutOfStockProduct);

        long productId = saveOutOfStockProduct.getId();
        int orderCount = 1;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);

        // when // then
        Mockito.when(paymentSystemClient.paymentUserPoint(anyLong(), anyInt()))
               .thenReturn(false);

        assertThatThrownBy(() -> orderService.createOrder(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 실패");

        productJpaRepository.deleteById(productId);
        userJpaRepository.deleteById(saveUser.getId());
    }

    @DisplayName("상품 주문을 성공한다.")
    @Test
    void createOrder() {
        // given
        String userName = "박지용";
        int userPoint = 10000000;

        Product saveOutOfStockProduct = createProduct();
        productJpaRepository.save(saveOutOfStockProduct);

        long productId = saveOutOfStockProduct.getId();
        int orderCount = 1;

        User saveUser = createUser(userName, userPoint);
        userJpaRepository.save(saveUser);

        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), orderServiceRequest);

        // when // then
        Mockito.when(paymentSystemClient.paymentUserPoint(anyLong(), anyInt()))
               .thenReturn(true);

        Order order = orderService.createOrder(request);

        assertThat(order).isNotNull();
        assertThat(order).extracting("id", "orderStatus")
            .contains(order.getId(), COMPLETED);
    }

    @DisplayName("주문 동시성 테스트")
    @Test
    @Transactional
    void createOrderAsync() {
        // given
//        User user1 = userJpaRepository.save(createUser("유저1", 10000000));
//        User user2 = userJpaRepository.save(createUser("유저2", 10000000));

        Product saveProduct = productJpaRepository.save(createTenStockProduct());

        int orderCount = 6;
        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(saveProduct.getId(), orderCount);

        CreateOrderServiceRequest request1 = createOrderPaymentServiceRequest(88, orderServiceRequest);
        CreateOrderServiceRequest request2 = createOrderPaymentServiceRequest(89, orderServiceRequest);

        // when
        CompletableFuture.allOf(
            CompletableFuture.runAsync(() -> orderService.createOrder(request1))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("1 주문 실패! : " + ex.getMessage());
                                 return "";
                             }),
            CompletableFuture.runAsync(() -> orderService.createOrder(request2))
                             .handle((result, ex) -> {
                                 if (ex != null) System.out.println("2 주문 실패! : " + ex.getMessage());
                                 return "";
                             })
        ).join();

        Product product = productJpaRepository.findById(saveProduct.getId())
                                              .orElseThrow(() -> new IllegalArgumentException("미존재 상품"));

        // then
        assertThat(product.getProductCount()).isEqualTo(saveProduct.getProductCount() - orderCount);
    }

    private OrderServiceRequest createOrderServiceRequest(long productId, int orderCount) {
        return OrderServiceRequest.builder()
                                  .productId(productId)
                                  .orderCount(orderCount)
                                  .build();
    }

    private CreateOrderServiceRequest createOrderPaymentServiceRequest(long userId, OrderServiceRequest order) {
        return CreateOrderServiceRequest.builder()
                                        .userId(userId)
                                        .orders(List.of(order))
                                        .build();
    }

    private User createUser(String userName, int userPoint) {
        return User.builder()
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }

    private Product createOutOfStockProduct() {
        return Product.builder()
                      .productName("재고가 부족한 상품")
                      .productDesc("재고가 부족한 상품 통합 테스트를 위한 상품 정보")
                      .productPrice(10000)
                      .productCount(0)
                      .build();
    }

    private Product createProduct() {
        return Product.builder()
                      .productName("통합 테스트 상품")
                      .productDesc("통합 테스트를 위한 상품 정보")
                      .productPrice(1000)
                      .productCount(100)
                      .build();
    }

    private Product createTenStockProduct() {
        return Product.builder()
                      .productName("통합 테스트 상품")
                      .productDesc("통합 테스트를 위한 상품 정보")
                      .productPrice(10)
                      .productCount(10)
                      .build();
    }
}
