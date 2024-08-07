package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomConflictException;
import com.hhplus.commerce.spring.api.order.infrastructure.client.DataPlatformClient;
import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.order.service.request.CreateOrderServiceRequest;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import com.hhplus.commerce.spring.api.order.service.request.OrderServiceRequest;
import com.hhplus.commerce.spring.api.order.model.OrderItem;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.order.repository.OrderRepository;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import jakarta.persistence.OptimisticLockException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode.PRODUCT_BAD_REQUEST;
import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode.USER_BAD_REQUEST;
import static com.hhplus.commerce.spring.api.common.presentation.exception.code.ConflictErrorCode.USER_POINT_DEDUCTION_CONFLICT;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final DataPlatformClient dataPlatformClient;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    // TODO: 결제와 재고차감과 데이터플랫폼으로 주문데이터 전송은 주문 생성의 하나의 로직이라고 볼 수 있다.
    @Transactional
    public Order createOrder(CreateOrderServiceRequest request) {
        // 사용자 포인트 낙관적 락 적용
        User user = userRepository.findByIdWithLock(request.getUserId())
                                  .orElseThrow(() -> new CustomBadRequestException(USER_BAD_REQUEST));

        List<Long> productIds = extractProductIds(request.getOrders());

        Map<Long, OrderServiceRequest> orderMap = createOrderServieMap(request.getOrders());

        /**
         * 재고 감소 -> 동시성 고민
         * optimistic lock / pessimistic lock / ...
         * 동시의 여러개의 주문이 들어오는 경우
         * 재고 10개 <- 1번 주문 재고 9개 구매, 2번 주문 재고 2개 구매인 경우
         */
        deductProductQuantities(productIds, orderMap);

        Map<Long, Product> productMap = createProductMap(productIds);

        Order saveOrder = orderRepository.save(Order.create(user));
        List<OrderItem> orderItems = createOrderItems(saveOrder, productIds, productMap, orderMap);
        saveOrder.getOrderItem().addAll(orderItems);

        int totalPrice = productTotalPrice(productIds, productMap);

        try {
            user.deductUserPoint(totalPrice);
        } catch (OptimisticLockException e) {
            throw new CustomConflictException(USER_POINT_DEDUCTION_CONFLICT);
        }

        saveOrder.orderStatusPaymentCompleted();

        eventPublisher.publishEvent(new OrderEvent(user.getId(), saveOrder.getId()));

        return saveOrder;
    }

    private int productTotalPrice(List<Long> productKeys, Map<Long, Product> productMap) {
        int totalPrice = 0;

        for (Long productId : new HashSet<>(productKeys)) {
            Product product = productMap.get(productId);

            totalPrice += product.getProductPrice();
        }

        return totalPrice;
    }

    private void deductProductQuantities(List<Long> productKeys, Map<Long, OrderServiceRequest> orderMap) {
        for (Long productId : new HashSet<>(productKeys)) {
            // 비관적 락 적용
            Product product = productRepository.findByIdWithPessimisticLock(productId)
                                               .orElseThrow(() -> new CustomBadRequestException(PRODUCT_BAD_REQUEST));

            int quantity = orderMap.get(productId)
                                   .getOrderCount();

            product.deductQuantity(quantity);
        }
    }

    private List<Long> extractProductIds(List<OrderServiceRequest> orders) {
        return orders.stream()
                     .map(o -> o.getProductId())
                     .collect(Collectors.toList());
    }

    private Map<Long, Product> createProductMap(List<Long> productKeys) {
        List<Product> products = productRepository.findAllByIdIn(productKeys);
        return products.stream()
                       .collect(Collectors.toMap(product -> product.getId(), p -> p));
    }

    private Map<Long, OrderServiceRequest> createOrderServieMap(List<OrderServiceRequest> orders) {
        return orders.stream()
                     .collect(Collectors.toMap(order -> order.getProductId(), o -> o));
    }

    private List<OrderItem> createOrderItems(Order order, List<Long> productKeys, Map<Long, Product> productMap,
                                             Map<Long, OrderServiceRequest> orderMap) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (Long productId : new HashSet<>(productKeys)) {
            Product product = productMap.get(productId);
            int orderCount = orderMap.get(productId).getOrderCount();

            orderItems.add(OrderItem.create(order, product, orderCount));
        }

        return orderItems;
    }
}
