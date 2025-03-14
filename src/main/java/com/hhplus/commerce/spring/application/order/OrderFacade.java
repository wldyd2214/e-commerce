package com.hhplus.commerce.spring.application.order;

import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.event.kafka.OrderEventProducer;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeRequestMapper;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand;
import com.hhplus.commerce.spring.domain.order.service.OrderService;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderFacade {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserService userService;

    private final ApplicationEventPublisher eventPublisher;
    private final OrderEventProducer orderEventProducer;

    private final OrderFacadeRequestMapper requestMapper;
    private final OrderFacadeResponseMapper responseMapper;

    @Transactional
    public OrderFacadeResponse.Create orderCreate(OrderFacadeRequest.Create create) {

        // 1. 상품 확인/재고 감소
//        ProductCommand.Deduct productCommand = requestMapper.toProductCommandDeduct(create);
//        ProductDeductInfo productDeductInfo = productService.deductProductQuantities(productCommand);

        List<Long> productIds = requestMapper.toProductIdList(create.getOrders());

        // 1. 주문 상품 유효성 체크
        productService.verifyProducts(productIds);

        // 2. 상품 목록 조회
        List<ProductInfo> productInfos = productService.getProducts(productIds);

        // 2. 주문 생성
        OrderCommand.Create orderCommand = requestMapper.toCreate(create);
        OrderInfo orderInfo = orderService.create(orderCommand, productInfos);

        // 3. 사용자 포인트 차감
//        int rewardPoints = productDeductInfo.getTotalAmount();
//        UserCommand.RewardPoint userCommand = requestMapper.toUserCommandReward(create.getUserId(), rewardPoints);
//        userService.useRewardPoints(userCommand);

        // 4. 외부 시스템 주문 생성 이벤트 발행
//        OrderCreateEvent orderEvent = OrderCreateEvent.create(create.getUserId(), orderInfo.getId());
        // 카프카 이벤트 (개선 후)
//        orderEventProducer.sendOrderCreateEvent(orderEvent);
        // 어플리케이션 이벤트 (개선 전)
//        eventPublisher.publishEvent(orderEvent);

        // 5. 도메인 서비스 응답 객체 변환
        return responseMapper.toCreate(orderInfo);
    }
}
