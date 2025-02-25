package com.hhplus.commerce.spring.application.order.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderCreateEvent {

    private Long userId;
    private Long orderId;

    public static OrderCreateEvent create(long userId, long orderId) {
        return new OrderCreateEvent(userId, orderId);
    }
}
