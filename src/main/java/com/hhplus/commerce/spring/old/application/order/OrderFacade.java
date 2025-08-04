package com.hhplus.commerce.spring.old.application.order;

import com.hhplus.commerce.spring.old.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.old.application.order.event.OrderEventProducer;
import com.hhplus.commerce.spring.old.application.order.mapper.OrderFacadeRequestMapper;
import com.hhplus.commerce.spring.old.application.order.mapper.OrderFacadeResponseMapper;
import com.hhplus.commerce.spring.old.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.old.domain.order.dto.request.OrderCommand;
import com.hhplus.commerce.spring.old.domain.order.service.OrderService;
import com.hhplus.commerce.spring.old.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.old.domain.product.service.ProductService;
import com.hhplus.commerce.spring.old.domain.user.service.UserService;
import com.hhplus.commerce.spring.old.application.order.dto.response.OrderFacadeResponse.Create;
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

    /**
     * 상품 주문시 상품의 재고를 점유하고 있어야하는 정책이 생기는 경우 어떻게 구현할 것 인지?
     */

    @Transactional
    public Create orderCreate(OrderFacadeRequest.Create create) {

        // 1. 상품 유효성 검사 (GRPC 통신을 가정하여 구현)
        List<Long> productIds = requestMapper.toProductIds(create);
        List<ProductInfo> productInfos = productService.getProducts(productIds);

        // 2. 상품 정보 조회

        // 3. 주문 생성
        OrderCommand.Create orderCommand = requestMapper.toCreate(create);
        OrderInfo orderInfo = orderService.create(orderCommand, productDeductInfo);
    }

//    @Transactional
//    public OrderFacadeResponse.Create orderCreate(OrderFacadeRequest.Create create) {
//
//        // 1. 상품 확인/재고 감소
//        // 상품 아이디, 이름, 가격 정보
////        ProductCommand.Deduct productCommand = requestMapper.toProductCommandDeduct(create);
////        ProductDeductInfo productDeductInfo = productService.deductProductQuantities(productCommand);
//
//        // 2. 주문 생성
//        OrderCommand.Create orderCommand = requestMapper.toCreate(create);
//        OrderInfo orderInfo = orderService.create(orderCommand, productDeductInfo);
//
//        // 3. 사용자 포인트 차감
//        int rewardPoints = productDeductInfo.getTotalAmount();
//        UserCommand.RewardPoint userCommand = requestMapper.toUserCommandReward(create.getUserId(), rewardPoints);
//        userService.useRewardPoints(userCommand);
//
//        // 4. 외부 시스템 주문 생성 이벤트 발행
//        OrderCreateEvent orderEvent = OrderCreateEvent.create(create.getUserId(), orderInfo.getId());
//        // 카프카 이벤트 (개선 후)
//        orderEventProducer.sendOrderCreateEvent(orderEvent);
//        // 어플리케이션 이벤트 (개선 전)
////        eventPublisher.publishEvent(orderEvent);
//
//        // 5. 도메인 서비스 응답 객체 변환
//        return responseMapper.toCreate(orderInfo);
//    }
}
