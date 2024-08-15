package com.hhplus.commerce.spring.api.order.event.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEventRequest {

    @JsonProperty("orderEvent")
    private OrderEvent orderEvent;
    @JsonProperty("messageId")
    private Long messageId;
}
