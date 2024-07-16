package com.hhplus.commerce.spring.api.order.service;

import com.hhplus.commerce.spring.api.order.infrastructure.client.DataPlatformClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DataPlatformService {

    private final DataPlatformClient dataPlatformClient;

    public boolean sendOrderData(Long userId, Long orderId) {

        boolean result = dataPlatformClient.sendOrderData(userId, orderId);

        if (result) {
            return true;
        }

        return false;
    }
}
