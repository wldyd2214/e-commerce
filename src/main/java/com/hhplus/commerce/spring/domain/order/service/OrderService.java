package com.hhplus.commerce.spring.domain.order.service;

import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand.Create;
import com.hhplus.commerce.spring.domain.order.mapper.OrderMapper;
import com.hhplus.commerce.spring.domain.order.model.Order;
import com.hhplus.commerce.spring.domain.order.model.OrderItem;
import com.hhplus.commerce.spring.domain.order.repository.OrderCommandRepository;
import com.hhplus.commerce.spring.domain.order.repository.OrderItemCommandRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductDeductInfo;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import java.util.ArrayList;
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

    private final OrderCommandRepository orderCommandRepository;
    private final OrderItemCommandRepository orderItemCommandRepository;

    private final OrderMapper orderMapper;

    // TODO: 결제와 재고차감과 데이터플랫폼으로 주문데이터 전송은 주문 생성의 하나의 로직이라고 볼 수 있다.
    @Transactional
    public OrderInfo create(OrderCommand.Create command, ProductDeductInfo productDeductInfo) {

        // 1. 주문 생성
        Order order = Order.create(command.getUserId());
        orderCommandRepository.save(order);

        // 2. 주문 아이템 생성
        Map<Long, Create.OrderItem> orderItemMap = createOrderItemMap(command.getOrders());
        Map<Long, ProductInfo> productItemMap = createProductItemMap(productDeductInfo.getProductInfos());

        List<OrderItem> orderItems = createOrderItems(order, orderItemMap, productItemMap);
        orderItemCommandRepository.saveAll(orderItems);

        // 3. 주문 엔티티 주문 아이템 업데이트
        order.updateOrderItems(orderItems);

        // 4. 응답 객체 변환
        return orderMapper.toOrderInfo(order);
    }

    private Map<Long, ProductInfo> createProductItemMap(List<ProductInfo> productInfos) {
        return productInfos.stream()
            .collect(Collectors.toMap(ProductInfo::getId, p -> p));
    }

    private Map<Long, Create.OrderItem> createOrderItemMap(List<Create.OrderItem> orders) {
        return orders.stream()
            .collect(Collectors.toMap(Create.OrderItem::getProductId, o -> o));
    }

    private List<OrderItem> createOrderItems(Order order, Map<Long, Create.OrderItem> orderItemMap, Map<Long, ProductInfo> productItemMap) {

        List<OrderItem> orderItems = new ArrayList<>();

        for (Long productId : orderItemMap.keySet()) {
            int orderCount = orderItemMap.get(productId).getOrderCount();
            String productName = productItemMap.get(productId).getName();
            int productPrice = productItemMap.get(productId).getPrice();

            OrderItem orderItem = OrderItem.create(order, productId, productName, productPrice, orderCount);
            orderItems.add(orderItem);
        }

        return orderItems;
    }
}
