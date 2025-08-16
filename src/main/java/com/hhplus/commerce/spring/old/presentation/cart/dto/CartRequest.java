package com.hhplus.commerce.spring.old.presentation.cart.dto;

import com.hhplus.commerce.spring.old.presentation.cart.dto.common.CartItemDTO;
import jakarta.persistence.ElementCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CartRequest {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class AddItem {
        @Valid
        private List<CartItemDTO> cartItems;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class RemoveItem {
        @Size(min = 1, message = "아이템 ID 목록은 최소 1개 이상이어야 합니다.")
        @NotNull(message = "아이템 ID 목록은 필수입니다.")
        @ElementCollection
        @Positive(message = "아이템 ID는 1 이상의 값이어야 합니다.")
        private List<Long> itemIds;
    }
}
