package com.hhplus.commerce.spring.api.cart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.old.api.cart.controller.CartController;
import com.hhplus.commerce.spring.domain.cart.CartService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected CartService cartService;

    @DisplayName("장바구니 목록 정보 조회에 성공한다.")
    @Test
    void getCartItems() throws Exception {
        // when // then
        mockMvc.perform(get("/carts/1/item"))
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       jsonPath("$.code").value("200"),
                       jsonPath("$.status").value("OK"),
                       jsonPath("$.message").value("OK"),
                       jsonPath("$.data").isNotEmpty()
               );
    }

//    @DisplayName("장바구니 목록 정보 추가에 성공한다.")
//    @Test
//    void addCartItems() throws Exception {
//        long userId = 1;
//        long productId = 1;
//        int orderCount = 2;
//
//        CartItemRegisterRequest cartItem = CartItemRegisterRequest.builder()
//                                                                  .productId(productId)
//                                                                  .orderCount(orderCount)
//                                                                  .build();
//
//        CartItemsRegisterRequest request = CartItemsRegisterRequest.builder()
//                                                                   .cartItems(
//                                                                           List.of(cartItem)
//                                                                   )
//                                                                   .build();
//
//        // when // then
//        mockMvc.perform(
//                post(String.format("/carts/%d/item", userId))
//                        .content(objectMapper.writeValueAsString(request))
//                        .contentType(MediaType.APPLICATION_JSON))
//               .andDo(print())
//               .andExpectAll(
//                       status().isOk(),
//                       jsonPath("$.code").value("200"),
//                       jsonPath("$.status").value("OK"),
//                       jsonPath("$.message").value("OK"),
//                       jsonPath("$.data").isNotEmpty()
//               );
//    }

//    @DisplayName("장바구니 목록 정보 삭제에 성공한다.")
//    @Test
//    void deleteCartItems() throws Exception {
//        long userId = 1;
//
//        List<Long> cartItemIds = List.of(1L, 2L, 3L);
//
//        CartItemRemoveRequest request = CartItemRemoveRequest.builder()
//                                                             .cartItemIds(cartItemIds)
//                                                             .build();
//        // when // then
//        mockMvc.perform(
//                       delete(String.format("/carts/%d/item", userId))
//                               .content(objectMapper.writeValueAsString(request))
//                               .contentType(MediaType.APPLICATION_JSON))
//               .andDo(print())
//               .andExpectAll(
//                       status().isOk(),
//                       jsonPath("$.code").value("200"),
//                       jsonPath("$.status").value("OK"),
//                       jsonPath("$.message").value("OK"),
//                       jsonPath("$.data").isEmpty()
//               );
//    }
}