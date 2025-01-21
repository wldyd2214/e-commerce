package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.infrastructure.order.repository.OrderJpaRepository;
import com.hhplus.commerce.spring.domain.order.service.OrderService;
import com.hhplus.commerce.spring.old.api.product.infrastructure.database.ProductJpaRepository;
import com.hhplus.commerce.spring.infrastructure.user.database.UserJpaRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ActiveProfiles("test")
@SpringBootTest
public class OrderServiceIntegrationTest {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderJpaRepository orderJpaRepository;

//    @DisplayName("유효하지 않은 사용자의 경우 주문에 실패한다.")
//    @Test
//    void invalidUserOrderPayment() {
//        // given
//        long userId = -1;
//        long productId = 1;
//        int orderCount = 1;
//
//        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, order);
//
//        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(USER_BAD_REQUEST.getMessage());
//    }
//
//    @DisplayName("사용자의 잔액이 부족한 경우 주문에 실패한다.")
//    @Test
//    void lockOfUserPointOrderPayment() {
//        // given
//        String userName = "잔액 부족 사용자";
//        int userPoint = 0;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        Product saveProduct = productJpaRepository.save(createProduct());
//        int orderCount = 1;
//
//        OrderServiceRequest order = createOrderServiceRequest(saveProduct.getId(), orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);
//
//        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(USER_POINT_BAD_REQUEST.getMessage());
//    }
//
//    @DisplayName("상품 재고가 부족한 경우 주문에 실패한다.")
//    @Test
//    void lackOfStockOrderPayment() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000000;
//
//        Product saveOutOfStockProduct = createOutOfStockProduct();
//        productJpaRepository.save(saveOutOfStockProduct);
//
//        long productId = saveOutOfStockProduct.getId();
//        int orderCount = 5;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);
//
//        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(PRODUCT_STOCK_BAD_REQUEST.getMessage());
//    }
//
////    @DisplayName("결제가 실패하는 경우 상품 주문에 실패한다.")
////    @Test
////    void orderPaymentFail() {
////        // given
////        String userName = "박지용";
////        int userPoint = 10000000;
////
////        Product saveOutOfStockProduct = createProduct();
////        productJpaRepository.save(saveOutOfStockProduct);
////
////        long productId = saveOutOfStockProduct.getId();
////        int orderCount = 1;
////
////        User saveUser = createUser(userName, userPoint);
////        userJpaRepository.save(saveUser);
////
////        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
////        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), order);
////
////        assertThatThrownBy(() -> orderService.createOrder(request))
////                .isInstanceOf(CustomBadGateWayException.class)
////                .hasMessage(PAYMENT_BAD_GATEWAY.getMessage());
////    }
//
//    @DisplayName("상품 주문을 성공한다.")
//    @Test
//    void createOrder() {
//        // given
//        String userName = "박지용";
//        int userPoint = 10000000;
//
//        Product saveOutOfStockProduct = createProduct();
//        productJpaRepository.save(saveOutOfStockProduct);
//
//        long productId = saveOutOfStockProduct.getId();
//        int orderCount = 1;
//
//        User saveUser = createUser(userName, userPoint);
//        userJpaRepository.save(saveUser);
//
//        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(saveUser.getId(), orderServiceRequest);
//
//        Order order = orderService.createOrder(request);
//
//        assertThat(order).isNotNull();
//        assertThat(order).extracting("id", "orderStatus")
//                         .contains(order.getId(), COMPLETED);
//    }
//
//    @DisplayName("주문 재고 동시성 테스트")
//    @Test
//    void createOrderStockAsync() {
//        // given
//        User user1 = userJpaRepository.save(createUser("유저1", 10000000));
//        User user2 = userJpaRepository.save(createUser("유저2", 10000000));
//
//        Product saveProduct = productJpaRepository.save(createTenStockProduct());
//
//        int orderCount = 6;
//        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(saveProduct.getId(), orderCount);
//
//        CreateOrderServiceRequest request1 = createOrderPaymentServiceRequest(user1.getId(), orderServiceRequest);
//        CreateOrderServiceRequest request2 = createOrderPaymentServiceRequest(user2.getId(), orderServiceRequest);
//
//        List<Order> orders = new ArrayList<>();
//
//        // when
//        CompletableFuture.allOf(
//            CompletableFuture.supplyAsync(() -> orderService.createOrder(request1))
//                             .handle((result, ex) -> {
//                                 if (ex != null) {
//                                     log.error("1. 주문 요청 실패 : {}", ex.getMessage());
//                                     return null;
//                                 }
//                                 return orders.add(result);
//                             }),
//            CompletableFuture.supplyAsync(() -> orderService.createOrder(request2))
//                             .handle((result, ex) -> {
//                                 if (ex != null) {
//                                     log.error("2. 주문 요청 실패 : {}", ex.getMessage());
//                                     return null;
//                                 }
//                                 return orders.add(result);
//                             })
//        ).join();
//
//        Product product = productJpaRepository.findById(saveProduct.getId())
//                                              .orElseThrow(() -> new IllegalArgumentException("미존재 상품"));
//
//        // then
//        assertThat(product.getProductCount()).isEqualTo(saveProduct.getProductCount() - orderCount);
//
//        orderJpaRepository.deleteAllByIdInBatch(orders.stream().map(o -> o.getId()).collect(Collectors.toList()));
//        productJpaRepository.deleteById(saveProduct.getId());
//        userJpaRepository.deleteAllByIdInBatch(List.of(user1.getId(), user2.getId()));
//    }
//
//    @DisplayName("주문 사용자 잔액 동시성 테스트")
//    @Test
//    void createOrderUserPointAsync() {
//        // given
//        User user1 = userJpaRepository.save(createUser("유저1", 10000000));
//
//        Product saveProduct = productJpaRepository.save(createTenStockProduct());
//
//        int orderCount = 1;
//        OrderServiceRequest orderServiceRequest1 = createOrderServiceRequest(saveProduct.getId(), orderCount);
//        OrderServiceRequest orderServiceRequest2 = createOrderServiceRequest(saveProduct.getId(), orderCount);
//
//        CreateOrderServiceRequest request1 = createOrderPaymentServiceRequest(user1.getId(), orderServiceRequest1);
//        CreateOrderServiceRequest request2 = createOrderPaymentServiceRequest(user1.getId(), orderServiceRequest2);
//
//        List<Order> orders = new ArrayList<>();
//
//        // when
//        CompletableFuture.allOf(
//                CompletableFuture.supplyAsync(() -> orderService.createOrder(request1))
//                                 .handle((result, ex) -> {
//                                     if (ex != null) {
//                                         log.error("1. 주문 요청 실패 : {}", ex.getMessage());
//                                         return null;
//                                     }
//                                     return orders.add(result);
//                                 }),
//                CompletableFuture.supplyAsync(() -> orderService.createOrder(request2))
//                                 .handle((result, ex) -> {
//                                     if (ex != null) {
//                                         log.error("2. 주문 요청 실패 : {}", ex.getMessage());
//                                         return null;
//                                     }
//                                     return orders.add(result);
//                                 })
//        ).join();
//
//        Product product = productJpaRepository.findById(saveProduct.getId())
//                                              .orElseThrow(() -> new IllegalArgumentException("미존재 상품"));
//
//        User findUser = userJpaRepository.findById(user1.getId())
//                                         .orElseThrow(() -> new IllegalArgumentException("미존재 사용자"));
//
//        // then
//        assertThat(findUser.getUserPoint()).isEqualTo(user1.getUserPoint() - saveProduct.getProductPrice());
//        assertThat(product.getProductCount()).isEqualTo(saveProduct.getProductCount() - orderCount);
//    }
//
//    private OrderServiceRequest createOrderServiceRequest(long productId, int orderCount) {
//        return OrderServiceRequest.builder()
//                                  .productId(productId)
//                                  .orderCount(orderCount)
//                                  .build();
//    }
//
//    private CreateOrderServiceRequest createOrderPaymentServiceRequest(long userId, OrderServiceRequest order) {
//        return CreateOrderServiceRequest.builder()
//                                        .userId(userId)
//                                        .orders(List.of(order))
//                                        .build();
//    }
//
//    private User createUser(String userName, int userPoint) {
//        return User.builder()
//                   .userName(userName)
//                   .userPoint(userPoint)
//                   .build();
//    }
//
//    private Product createOutOfStockProduct() {
//        return Product.builder()
//                      .productName("재고가 부족한 상품")
//                      .productDesc("재고가 부족한 상품 통합 테스트를 위한 상품 정보")
//                      .productPrice(10000)
//                      .productCount(0)
//                      .build();
//    }
//
//    private Product createProduct() {
//        return Product.builder()
//                      .productName("통합 테스트 상품")
//                      .productDesc("통합 테스트를 위한 상품 정보")
//                      .productPrice(1000)
//                      .productCount(100)
//                      .build();
//    }
//
//    private Product createTenStockProduct() {
//        return Product.builder()
//                      .productName("통합 테스트 상품")
//                      .productDesc("통합 테스트를 위한 상품 정보")
//                      .productPrice(10)
//                      .productCount(10)
//                      .build();
//    }
}
