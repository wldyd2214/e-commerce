package com.hhplus.commerce.spring.api.order.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.hhplus.commerce.spring.domain.order.repository.OrderCommandRepository;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.domain.user.repository.UserQueryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    UserQueryRepository userQueryRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderCommandRepository orderCommandRepository;
//    @Mock
//    DataPlatformService dataPlatformService;
//    @InjectMocks
//    OrderService orderService;

    @DisplayName("유효하지 않은 사용자의 경우 주문에 실패한다.")
    @Test
    void invalidUserOrderPayment() {

        // given
        long userId = 1;
        long productId = 1;
        int orderCount = 1;

//        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, order);

//        given(userRepository.findByIdWithLock(anyLong())).willThrow(new CustomBadRequestException(USER_BAD_REQUEST));

        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//            .isInstanceOf(CustomBadRequestException.class)
//            .hasMessage(USER_BAD_REQUEST.getMessage());
    }

//    @DisplayName("사용자의 잔액이 부족한 경우 주문에 실패한다.")
//    @Test
//    void lockOfUserPointOrderPayment() {
//
//        // given
//        long userId = 1;
//        String userName = "박지용";
//        int userPoint = 0;
//
//        User user = createUser(userId, userName, userPoint);
//
//        long productId = 1;
//        int productPrice = 100000;
//        int stockCount = 1;
//
//        Product product = createProduct(productId, productPrice, stockCount);
//
//        int orderCount = 1;
//
//        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

//        given(userRepository.findByIdWithLock(anyLong())).willReturn(Optional.ofNullable(user));

//        given(productRepository.findAllByIdIn(any())).willReturn(List.of(product));
//
//        given(productRepository.findByIdWithPessimisticLock(anyLong())).willReturn(Optional.ofNullable(product));

//        Order order = Order.create(user);
//        order.setId(1L);
//        given(orderRepository.save(any())).willReturn(order);
//
//        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//            .isInstanceOf(CustomBadRequestException.class)
//            .hasMessage(USER_INSUFFICIENT_BALANCE_BAD_REQUEST.getMessage());
//    }

//    @DisplayName("상품 재고가 부족한 경우 주문에 실패한다.")
//    @Test
//    void lackOfStockOrderPayment() {

        // given
//        long userId = 1;
//        String userName = "박지용";
//        int userPoint = 0;
//
//        User user = createUser(userId, userName, userPoint);
//
//        long productId = 1;
//        int productPrice = 100000;
//        int stockCount = 0;
//
//        Product product = createProduct(productId, productPrice, stockCount);
//
//        int orderCount = 1;
//
//        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

//        given(userRepository.findByIdWithLock(anyLong())).willReturn(Optional.ofNullable(user));

//        given(productRepository.findByIdWithPessimisticLock(any())).willReturn(Optional.ofNullable(product));

        // when // then
//        assertThatThrownBy(() -> orderService.createOrder(request))
//                .isInstanceOf(CustomBadRequestException.class)
//                .hasMessage(PRODUCT_STOCK_BAD_REQUEST.getMessage());
//    }

//    @DisplayName("상품 주문에 성공한다.")
//    @Test
//    void OrderPayment() {

        // given
//        long userId = 1;
//        String userName = "박지용";
//        int userPoint = 100000;
//
//        User user = createUser(userId, userName, userPoint);
//
//        long productId = 1;
//        int productPrice = 100000;
//        int stockCount = 1;
//
//        Product product = createProduct(productId, productPrice, stockCount);
//
//        int orderCount = 1;
//
//        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
//        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

//        given(userRepository.findByIdWithLock(anyLong())).willReturn(Optional.ofNullable(user));
//        given(productRepository.findByIdWithPessimisticLock(anyLong())).willReturn(Optional.ofNullable(product));
//        given(productRepository.findAllByIdIn(anyList())).willReturn(List.of(product));

//        Order order = Order.create(user);
//        order.setId(1L);
//        given(orderRepository.save(any())).willReturn(order);
//        given(dataPlatformService.sendOrderData(any(), any())).willReturn(true);
//
//        Order resultOrder = orderService.createOrder(request);
//        assertThat(resultOrder).isNotNull();
//    }

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

//    private User createUser(long userId, String userName, Integer userPoint) {
//        return User.builder()
//                   .id(userId)
////                   .userName(userName)
////                   .userPoint(userPoint)
//                   .build();
//    }
//
//    private Product createProduct(long id, int productPrice, int stockCount) {
//        return Product.builder()
//                      .id(id)
//                      .productName(String.format("%d번 제품 이름", id))
//                      .productDesc(String.format("%d번 제품 내용", id))
//                      .productPrice(productPrice)
//                      .productCount(stockCount)
//                      .build();
//    }
}