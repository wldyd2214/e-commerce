package com.hhplus.commerce.spring.application.cart;

import com.hhplus.commerce.spring.application.cart.dto.request.CartFacadeRequest;
import com.hhplus.commerce.spring.application.cart.dto.response.CartFacadeResponse;
import com.hhplus.commerce.spring.application.cart.mapper.CartFacadeRequestMapper;
import com.hhplus.commerce.spring.domain.cart.CartService;
import com.hhplus.commerce.spring.domain.cart.dto.request.CartCommand;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.domain.user.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartFacade {

    private final UserService userService;
    private final ProductService productService;
    private final CartService cartService;

    private final CartFacadeRequestMapper requestMapper;

    public CartFacadeResponse.AddItem processAddCartItems(CartFacadeRequest.AddItem request) {

        // 1. 사용자 정보 조회
        UserInfo userInfo = userService.findUserInfoById(request.getUserId());

        // 2. 상품 정보 조회
        List<Long> productIds = requestMapper.toProductIds(request.getCartItems());
        List<ProductInfo> productInfos = productService.getProducts(productIds);

        // 3. 장바구니 리스트 추가
        // 조회된 상품을 기반으로 장바구니에 담은 갯수 셋팅해서 내려줘야함.

        CartCommand.AddItem command = requestMapper.toCartCommandAddItem(userInfo, productInfos, request.getCartItems());
        cartService.addCartItems(command);

        // 상품아이디
        // 상품명
        // 상품금액
        // 주문갯수
        return null;
    }
}
