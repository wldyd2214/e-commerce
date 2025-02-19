package com.hhplus.commerce.spring.domain.order.service;

import com.hhplus.commerce.spring.domain.order.dto.OrderInfo;
import com.hhplus.commerce.spring.domain.order.dto.request.OrderCommand;

public interface OrderService {
    OrderInfo order(OrderCommand.Order command);
}
