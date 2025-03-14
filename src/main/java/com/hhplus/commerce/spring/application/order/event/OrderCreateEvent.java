package com.hhplus.commerce.spring.application.order.event;

import com.hhplus.commerce.spring.domain.order.event.PointDeductionInfo;
import com.hhplus.commerce.spring.domain.order.event.StockDeductionInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateEvent {

    // 상품 재고 차감을 위한 정보
    private StockDeductionInfo stockDeductionInfo; // 상품 재고 차감을 위한 정보

    // 사용자 포인트 차감을 위한 정보
    private PointDeductionInfo pointDeductionInfo; // 사용자 포인트 차감을 위한 정보

    // 외부 시스템 데이터 플랫폼 주문 정보 전송을 위한 정보
    private Long userId;
    private Long orderId;

    public static OrderCreateEvent create(long userId, long orderId) {
        return new OrderCreateEvent(userId, orderId);
    }
}
