package com.hhplus.commerce.spring.support.sample;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private OrderNo number;
    private Orderer orderer;
    private List<OrderLine> orderLines;
    private Money totalAmounts;
    private ShippingInfo shippingInfo;
    private OrderState state;
    private LocalDateTime orderDate;


    public Order(List<OrderLine> orderLines) {
        setOrderLines(orderLines);
        setShippingInfo(shippingInfo);
    }

    private void setOrderLines(List<OrderLine> orderLines) {
        verifyAtLeastOneOrMoreOrderLines(orderLines);
        this.orderLines = orderLines;
        // 총 주문 금액은 각 상품의 구매 가격 합을 더한 금액이다.
        calculateTotalAmounts();
    }

    private void setShippingInfo(ShippingInfo shippingInfo) {
        if (shippingInfo == null)
            throw new IllegalArgumentException("no ShippingInfo");
        this.shippingInfo = shippingInfo;
    }

    private void verifyAtLeastOneOrMoreOrderLines(List<OrderLine> orderLines) {
        // 최소 한 종류 이상의 상품을 주문해야 한다.
        if (orderLines == null || orderLines.isEmpty()) {
            throw new IllegalArgumentException("no OrderLine");
        }
    }

    private void calculateTotalAmounts() {
        int sum = orderLines.stream()
                            .mapToInt(x -> x.getAmounts().getValue())
                            .sum();
        this.totalAmounts = new Money(sum);
    }

    public void cancel() {
        verifyNotYetShipped(); // 주문 취소 기능은 출고 전에만 가능
        this.state = OrderState.CANCELED;
    }

    private void verifyNotYetShipped() {
        if (state != OrderState.PAYMENT_WAITING && state != OrderState.PREPARING)
            throw new IllegalStateException("aleady shipped");
    }

    public void changeShippingInfo(ShippingInfo newShippingInfo) {
        if (!isShippingChangeable()) {
            throw new IllegalStateException("...");
        }
        this.shippingInfo = newShippingInfo;
    }

    private boolean isShippingChangeable() {
        return state == OrderState.PAYMENT_WAITING ||
            state == OrderState.PREPARING;
    }
}
