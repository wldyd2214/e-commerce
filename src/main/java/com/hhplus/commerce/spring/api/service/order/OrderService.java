package com.hhplus.commerce.spring.api.service.order;

import com.hhplus.commerce.spring.api.service.data.DataPlatformService;
import com.hhplus.commerce.spring.api.service.order.request.OrderPaymentServiceRequest;
import com.hhplus.commerce.spring.api.service.order.request.OrderServiceRequest;
import com.hhplus.commerce.spring.api.service.payment.PaymentService;
import com.hhplus.commerce.spring.model.entity.Order;
import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.model.entity.Payment;
import com.hhplus.commerce.spring.model.entity.Product;
import com.hhplus.commerce.spring.model.entity.User;
import com.hhplus.commerce.spring.repository.OrderItemRepository;
import com.hhplus.commerce.spring.repository.OrderRepository;
import com.hhplus.commerce.spring.repository.PaymentRepository;
import com.hhplus.commerce.spring.repository.ProductRepository;
import com.hhplus.commerce.spring.repository.UserRepository;
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
    private final PaymentRepository paymentRepository;

    @Transactional
    public List<OrderItem> orderPayment(OrderPaymentServiceRequest request) {

        User user = userRepository.findById(request.getUserId())
                                  .orElseThrow(() -> new IllegalArgumentException("존재하지 않은 사용자"));

        List<Long> productKeys = extractStockProductNumbers(request.getOrders());

        Map<Long, Product> productMap = createStockMapBy(productKeys);
        Map<Long, OrderServiceRequest> orderMap = createCountingMapBy(request.getOrders());

        int payMoney = deductStockQuantities(productKeys, productMap, orderMap);

        if (user.getUserPoint() < payMoney) {
            throw new IllegalArgumentException("사용자 잔액 부족");
        }

        Order order = Order.create(user);
        Order saveOrder = orderRepository.save(order);

        List<OrderItem> orderItems = createOrderItems(order, productKeys, productMap, orderMap);

        boolean payResult = paymentService.pointPayment(user.getId(), user.getUserPoint(), payMoney);

        Payment payment = Payment.create(order, payMoney);

        if (!payResult) {
            payment.paymentStatusFailed();
            paymentRepository.save(payment);

            saveOrder.orderStatusPaymentFail();
            throw new IllegalArgumentException("결제 실패");
        }

        payment.paymentStatusCompleted();
        paymentRepository.save(payment);

        user.UserPointDeduction(payMoney);

        boolean platformResult = dataPlatformService.sendOrderData(user.getId(), order.getId());

        if (!platformResult) {
            log.info("데이터 플랫폼 전송 실패!");
        }

        return orderItems;
    }

    private int deductStockQuantities(List<Long> productKeys, Map<Long, Product> productMap,
        Map<Long, OrderServiceRequest> orderMap) {
        Integer payMoney = 0;

        for (Long productId : new HashSet<>(productKeys)) {
            Product product = productMap.get(productId);
            int quantity = orderMap.get(productId).getOrderCount();

            if (product.isQuantityLessThan(quantity)) {
                throw new IllegalArgumentException("재고가 부족한 상품이 있습니다.");
            }

            payMoney += product.getProductPrice() * quantity;

            product.deductQuantity(quantity);
        }

        return payMoney;
    }

    private List<Long> extractStockProductNumbers(List<OrderServiceRequest> orders) {
        return orders.stream()
                     .map(o -> o.getProductId())
                     .collect(Collectors.toList());
    }

    private Map<Long, Product> createStockMapBy(List<Long> productKeys) {
        List<Product> products = productRepository.findAllByIdIn(productKeys);
        return products.stream()
                       .collect(Collectors.toMap(product -> product.getId(), p -> p));
    }

    private Map<Long, OrderServiceRequest> createCountingMapBy(List<OrderServiceRequest> orders) {
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
