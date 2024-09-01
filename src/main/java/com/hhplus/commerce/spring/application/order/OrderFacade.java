package com.hhplus.commerce.spring.application.order;

import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeRequestMapper;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderFacade {
    private final OrderService orderService;

    public OrderFacadeResponse order(OrderFacadeRequest request) {
        return OrderFacadeResponseMapper.toOrder(
            orderService.order(OrderFacadeRequestMapper.toOrder(request))
        );
    }
}
