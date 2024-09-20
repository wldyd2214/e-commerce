package com.hhplus.commerce.spring.domain.order.model;

import com.hhplus.commerce.spring.domain.order.model.type.State;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private Long id;
    private Long userId;
    private State state;
    private List<OrderItem> orderItem = new ArrayList<>();
}
