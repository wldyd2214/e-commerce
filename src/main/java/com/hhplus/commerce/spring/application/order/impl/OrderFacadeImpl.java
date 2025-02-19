package com.hhplus.commerce.spring.application.order.impl;

import com.hhplus.commerce.spring.application.order.OrderFacade;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeRequestMapper;
import com.hhplus.commerce.spring.application.order.mapper.OrderFacadeResponseMapper;
import com.hhplus.commerce.spring.domain.order.service.OrderService;
import com.hhplus.commerce.spring.old.api.product.service.ProductOldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final ProductOldService productOldService;

    public OrderFacadeResponse order(OrderFacadeRequest request) {

        return OrderFacadeResponseMapper.toOrder(orderService.order(OrderFacadeRequestMapper.toOrder(request)));
    }
}
