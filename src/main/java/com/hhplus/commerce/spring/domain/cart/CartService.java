package com.hhplus.commerce.spring.domain.cart;

import com.hhplus.commerce.spring.domain.cart.dto.common.CartInfo;
import com.hhplus.commerce.spring.domain.cart.dto.CartCommand;
import com.hhplus.commerce.spring.domain.cart.mapper.CartServiceResponseMapper;
import com.hhplus.commerce.spring.domain.cart.model.Cart;
import com.hhplus.commerce.spring.domain.cart.model.CartItem;
import com.hhplus.commerce.spring.domain.cart.model.CartProduct;
import com.hhplus.commerce.spring.domain.cart.model.CartUser;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartItemCommandRepository;
import com.hhplus.commerce.spring.domain.cart.repository.command.CartCommandRepository;
import com.hhplus.commerce.spring.domain.cart.dto.response.CartServiceRes;
import com.hhplus.commerce.spring.domain.cart.repository.query.CartQueryRepository;
import com.hhplus.commerce.spring.domain.product.dto.ProductInfo;
import com.hhplus.commerce.spring.domain.user.dto.UserInfo;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadGateWayException;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartCommandRepository cartCommandRepository;
    private final CartQueryRepository cartQueryRepository;
    private final CartItemCommandRepository cartItemCommandRepository;

    private final CartServiceResponseMapper responseMapper;

    @Transactional(readOnly = true)
    public CartInfo getCartInfoByUserId(Long userId) {

        Cart cart = cartQueryRepository.findByUserId(userId)
                                       .orElseThrow(() -> new CustomBadRequestException(BadRequestErrorCode.CART_BAD_REQUEST));

        return responseMapper.toCartInfo(cart);
    }

//    @Transactional
//    public CartServiceRes getCart(Long userId) {
//
//        Cart cart = cartCommandRepository.findByUser(user)
//                                         .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.CART_BAD_REQUEST));
//
//        return CartServiceRes.toCartServiceRes(cart);
//    }

    @Transactional
    public CartInfo addCartItems(CartCommand.AddItem command) {

        Long userId = command.getUserInfo().getId();

        Cart cart = cartQueryRepository.findByUserId(userId)
                                       .orElse(createCart(command.getUserInfo()));

        List<CartItem> cartItems = insertCartItem(cart, command.getProductInfos(), command.getCartItems());
        cart.getCartItems().addAll(cartItems);

        return responseMapper.toCartInfo(cart);
    }

    private Cart createCart(UserInfo userInfo) {

        CartUser cartUser = CartUser.create(userInfo.getId());

        Cart cart = Cart.create(cartUser);
        cartCommandRepository.save(cart);

        return cart;
    }

    private List<CartItem> insertCartItem(Cart cart, List<ProductInfo> productInfos, List<CartCommand.CartItem> commandCartItems) {


        List<CartItem> cartItems = new ArrayList<>();

        Map<Long, CartCommand.CartItem> cartItemMap = createCartItemMap(commandCartItems);

        for (ProductInfo productInfo : productInfos) {
            CartProduct product = CartProduct.create(productInfo.getId(), productInfo.getName(), productInfo.getPrice());
            CartCommand.CartItem item = cartItemMap.get(product.getId());
            cartItems.add(CartItem.create(cart, product, item.getCartQuantity()));
        }

        return cartItemCommandRepository.saveAll(cartItems);
    }

    private Map<Long, CartCommand.CartItem> createCartItemMap(List<CartCommand.CartItem> commandCartItems) {
        return commandCartItems.stream()
            .collect(Collectors.toMap(CartCommand.CartItem::getProductId, i -> i));
    }

    @Transactional
    public CartServiceRes removeCartItems(Long userId, List<Long> cartItemKeys) {

//        User user = userRepository.findById(userId)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.USER_BAD_REQUEST));
//
//        List<CartItem> cartItems = cartItemRepository.findAllById(cartItemKeys);
//        sameUserCheck(user, cartItems, cartItemKeys);
//
//        cartItemRepository.deleteAllByIdInBatch(cartItemKeys);
//
//        Cart cart = cartRepository.findByUser(user)
//                                  .orElseThrow(() -> new CustomBadRequestException(
//                                      BadRequestErrorCode.CART_ITEM_BAD_REQUEST));
//
//        return CartServiceRes.toCartServiceRes(cart);

        return null;
    }

//    private void sameUserCheck(User user, List<CartItem> cartItems, List<Long> cartItemKeys) {
//        Map<Long, CartItem> cartItemMap = createProductMap(cartItems);
//        for (Long cartItemKey : new HashSet<>(cartItemKeys)) {
//            CartItem cartItem = cartItemMap.get(cartItemKey);
//
//            if (Objects.isNull(cartItem) || cartItem.getCart().getUser().getId() != user.getId()) {
//                throw new CustomForbiddenException(ForbiddenErrorCode.USER_FORBIDDEN);
//            }
//        }
//    }

    private Map<Long, CartItem> createProductMap(List<CartItem> cartItems) {
        return cartItems.stream()
                        .collect(Collectors.toMap(item -> item.getId(), i -> i));
    }
}
