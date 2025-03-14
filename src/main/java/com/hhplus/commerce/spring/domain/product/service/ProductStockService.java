package com.hhplus.commerce.spring.domain.product.service;

import com.hhplus.commerce.spring.domain.order.event.StockDeductionInfo;
import org.springframework.stereotype.Service;

@Service
public class ProductStockService {

    public void deductStock(StockDeductionInfo stockDeductionInfo) {
        // 재고 차감 로직
    }
}
