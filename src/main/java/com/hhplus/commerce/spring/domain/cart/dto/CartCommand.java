package com.hhplus.commerce.spring.domain.cart.dto;

import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartCommand {

    @AllArgsConstructor
    @Getter
    public static class AddItem {
        private UserInfo userInfo;
        private List<ProductInfo> productInfos;
        private List<CartCommand.CartItem> cartItems;
    }

    @AllArgsConstructor
    @Getter
    public static class CartItem {
        private Long productId;
        private Integer cartQuantity;
    }
}
