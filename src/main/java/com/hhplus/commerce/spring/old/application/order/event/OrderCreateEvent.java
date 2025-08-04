package com.hhplus.commerce.spring.old.application.order.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateEvent {

    private Long userId;
    private Long orderId;

    public static OrderCreateEvent create(long userId, long orderId) {
        return new OrderCreateEvent(userId, orderId);
    }
}
