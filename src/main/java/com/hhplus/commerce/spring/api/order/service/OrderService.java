package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.order.service.request.CreateOrderServiceRequest;
import com.hhplus.commerce.spring.api.order.service.request.OrderServiceRequest;
import com.hhplus.commerce.spring.api.order.model.Order;
import com.hhplus.commerce.spring.api.order.model.OrderItem;
import com.hhplus.commerce.spring.api.product.model.Product;
import com.hhplus.commerce.spring.api.user.model.User;
import com.hhplus.commerce.spring.api.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.api.order.repository.OrderRepository;
import com.hhplus.commerce.spring.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.api.user.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderService {
    private final PaymentService paymentService;
    private final DataPlatformService dataPlatformService;

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    // TODO: 결제와 재고차감과 데이터플랫폼으로 주문데이터 전송은 주문 생성의 하나의 로직이라고 볼 수 있다.
    @Transactional
    public Order createOrder(CreateOrderServiceRequest request) {

        User user = userRepository.findById(request.getUserId())
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        List<Long> productIds = extractProductIds(request.getOrders());

        Map<Long, Product> productMap = createProductMap(productIds);
        Map<Long, OrderServiceRequest> orderMap = createOrderServieMap(request.getOrders());

        int totalPrice = productTotalPrice(productIds, productMap);

        deductProductQuantities(productIds, productMap, orderMap);

        Order saveOrder = orderRepository.save(Order.create(user));
        List<OrderItem> orderItems = createOrderItems(saveOrder, productIds, productMap, orderMap);
        saveOrder.getOrderItem().addAll(orderItems);

        paymentService.paymentUserPoint(user.getId(), totalPrice, saveOrder);
        user.UserPointDeduction(totalPrice);

        boolean dataResult = dataPlatformService.sendOrderData(user.getId(), saveOrder.getId());

        log.info(String.format("데이터 플랫폼 전송 결과 : %s ", dataResult));

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

    private void deductProductQuantities(List<Long> productKeys, Map<Long, Product> productMap,
        Map<Long, OrderServiceRequest> orderMap) {

        for (Long productId : new HashSet<>(productKeys)) {
            Product product = productMap.get(productId);
            int quantity = orderMap.get(productId).getOrderCount();

            if (product.isQuantityLessThan(quantity)) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
            }

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

        if (orderItems.size() > 0) {
            return orderItemRepository.saveAll(orderItems);
        }

        return orderItems;
    }
}
