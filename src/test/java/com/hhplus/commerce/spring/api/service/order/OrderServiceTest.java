package com.hhplus.commerce.spring.api.service.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import com.hhplus.commerce.spring.api.order.service.DataPlatformService;
import com.hhplus.commerce.spring.api.order.service.OrderService;
import com.hhplus.commerce.spring.api.order.service.request.CreateOrderServiceRequest;
import com.hhplus.commerce.spring.api.order.service.request.OrderServiceRequest;
import com.hhplus.commerce.spring.api.order.service.PaymentService;
import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.order.model.OrderItem;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.api.order.repository.OrderRepository;
import com.hhplus.commerce.spring.api.user.repository.PaymentRepository;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    OrderRepository orderRepository;
    @Mock
    OrderItemRepository orderItemRepository;
    @Mock
    PaymentRepository paymentRepository;

    @Mock
    DataPlatformService dataPlatformService;
    @Mock
    PaymentService paymentService;
    @InjectMocks
    OrderService orderService;

    @DisplayName("유효하지 않은 사용자의 경우 주문에 실패한다.")
    @Test
    void invalidUserOrderPayment() {

        // given
        long userId = 1;
        long productId = 1;
        int orderCount = 1;

        OrderServiceRequest order = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, order);

        given(userRepository.findById(anyLong()))
            .willThrow(new IllegalArgumentException("존재하지 않은 사용자"));

        // when // then
        assertThatThrownBy(() -> orderService.orderPayment(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("존재하지 않은 사용자");
    }

    @DisplayName("사용자의 잔액이 부족한 경우 주문에 실패한다.")
    @Test
    void lockOfUserPointOrderPayment() {

        // given
        long userId = 1;
        String userName = "박지용";
        int userPoint = 0;

        User user = createUser(userId, userName, userPoint);

        long productId = 1;
        int productPrice = 100000;
        int stockCount = 1;

        Product product = createProduct(productId, productPrice, stockCount);

        int orderCount = 1;

        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

        given(userRepository.findById(anyLong())).willReturn(
            Optional.ofNullable(user));

        given(productRepository.findAllByIdIn(anyList())).willReturn(
            List.of(product));

        // when // then
        assertThatThrownBy(() -> orderService.orderPayment(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("사용자 잔액 부족");
    }

    @DisplayName("상품 재고가 부족한 경우 주문에 실패한다.")
    @Test
    void lackOfStockOrderPayment() {

        // given
        long userId = 1;
        String userName = "박지용";
        int userPoint = 0;

        User user = createUser(userId, userName, userPoint);

        long productId = 1;
        int productPrice = 100000;
        int stockCount = 0;

        Product product = createProduct(productId, productPrice, stockCount);

        int orderCount = 1;

        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

        given(userRepository.findById(anyLong())).willReturn(
            Optional.ofNullable(user));

        given(productRepository.findAllByIdIn(anyList())).willReturn(
            List.of(product));

        // when // then
        assertThatThrownBy(() -> orderService.orderPayment(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("재고가 부족한 상품이 있습니다.");
    }

    @DisplayName("결제가 실패하는 경우 상품 주문에 실패한다.")
    @Test
    void OrderPaymentFail() {

        // given
        long userId = 1;
        String userName = "박지용";
        int userPoint = 1000000;

        User user = createUser(userId, userName, userPoint);

        long productId = 1;
        int productPrice = 100000;
        int stockCount = 1;

        Product product = createProduct(productId, stockCount, productPrice);

        int orderCount = 1;

        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

        given(userRepository.findById(anyLong())).willReturn(
            Optional.ofNullable(user));

        given(productRepository.findAllByIdIn(anyList())).willReturn(
            List.of(product));

        Order order = Order.create(user);
        order.setId(1L);
        given(orderRepository.save(any())).willReturn(order);

        OrderItem orderItem = new OrderItem( order, product, product.getProductName(), product.getProductPrice(), orderCount);
        orderItem.setId(1L);
        given(orderItemRepository.saveAll(any())).willReturn(List.of(orderItem));

        given(paymentService.pointPayment(anyLong(), anyInt(), anyInt())).willReturn(false);

        // when // then
        assertThatThrownBy(() -> orderService.orderPayment(request))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 실패");
    }

    @DisplayName("상품 주문에 성공한다.")
    @Test
    void OrderPayment() {

        // given
        long userId = 1;
        String userName = "박지용";
        int userPoint = 100000;

        User user = createUser(userId, userName, userPoint);

        long productId = 1;
        int productPrice = 100000;
        int stockCount = 1;

        Product product = createProduct(productId, productPrice, stockCount);

        int orderCount = 1;

        OrderServiceRequest orderServiceRequest = createOrderServiceRequest(productId, orderCount);
        CreateOrderServiceRequest request = createOrderPaymentServiceRequest(userId, orderServiceRequest);

        given(userRepository.findById(anyLong())).willReturn(
            Optional.ofNullable(user));

        given(productRepository.findAllByIdIn(anyList())).willReturn(
            List.of(product));

        Order order = Order.create(user);
        order.setId(1L);
        given(orderRepository.save(any())).willReturn(order);

        OrderItem orderItem = new OrderItem( order, product, product.getProductName(), product.getProductPrice(), orderCount);
        orderItem.setId(1L);
        given(orderItemRepository.saveAll(any())).willReturn(List.of(orderItem));

        given(paymentService.pointPayment(anyLong(), anyInt(), anyInt())).willReturn(true);

        List<OrderItem> orderItems = orderService.orderPayment(request);

        assertThat(orderItems).isNotEmpty();
        assertThat(orderItems).hasSize(1);
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

    private User createUser(long userId, String userName, Integer userPoint) {
        return User.builder()
                   .id(userId)
                   .userName(userName)
                   .userPoint(userPoint)
                   .build();
    }

    private Product createProduct(long id, int productPrice, int stockCount) {
        return Product.builder()
                      .id(id)
                      .productName(String.format("%d번 제품 이름", id))
                      .productDesc(String.format("%d번 제품 내용", id))
                      .productPrice(productPrice)
                      .productCount(stockCount)
                      .build();
    }
}