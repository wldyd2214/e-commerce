package com.hhplus.commerce.spring.application.order;

import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;

public interface OrderFacade {
    OrderFacadeResponse order(OrderFacadeRequest request);
}
