package com.hhplus.commerce.spring.application.order.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderTopicType {
    ORDER_CREATED_TOPIC("order-created-topic", "주문 생성 토픽");

    private String topic;
    private String desc;
}
