package com.hhplus.commerce.spring.support.sample;

import lombok.Getter;

@Getter
public class OrderLine {
    private ProductId productId; // 상품
    private Money price; // 얼마에
    private int quantity; // 몇 개 살지
    private Money amounts;

    public OrderLine(ProductId productId, Money price, int quantity) {
        this.productId = productId;
		this.price = price;
        this.quantity = quantity;
    }

    // 구매 가격을 구하는 로직을 구현
    private Money calculateAmounts() {
        return price.multiply(quantity);
    }

    public Money getAmounts() { return amounts; }
}
