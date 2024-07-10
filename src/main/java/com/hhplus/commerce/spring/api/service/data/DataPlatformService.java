package com.hhplus.commerce.spring.api.service.data;

import com.hhplus.commerce.spring.infrastructure.data.DataPlatformClient;
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
