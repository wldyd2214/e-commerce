package com.hhplus.commerce.spring.domain.cart.dto.request;

import com.hhplus.commerce.spring.domain.cart.dto.CartItemInfo;
import com.hhplus.commerce.spring.domain.cart.dto.CartUserInfo;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CartCommand {

    @AllArgsConstructor
    @Getter
    public static class AddItem {
        private CartUserInfo userInfo;
        private List<CartItemInfo> cartItems;
    }
}
