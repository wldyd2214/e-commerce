package com.hhplus.commerce.spring.api.order.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.presentation.order.OrderController;
import com.hhplus.commerce.spring.presentation.order.dto.OrderPaymentDTO;
import com.hhplus.commerce.spring.presentation.order.dto.request.OrderRequest;
import com.hhplus.commerce.spring.domain.order.model.Order;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

//    @MockBean
//    protected OrderService orderService;

    @DisplayName("유효하지 않은 사용자의 경우 주문을 실패한다.")
    @Test
    void positiveUserIdCreateOrder() throws Exception {
        // given
        long userId = -1;

        int orderCount = 1;
        long productId = 1;
        OrderPaymentDTO orderPayment1 = createOrderPaymentDTO(orderCount, productId);

        OrderRequest request = OrderRequest.builder()
                                                 .userId(userId)
                                                 .orderItems(List.of(orderPayment1))
                                                 .build();

        // when // then
        mockMvc.perform(
                       post("/orders")
                               .content(objectMapper.writeValueAsString(request))
                               .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                       status().isBadRequest(),
                       jsonPath("$.code").value("400"),
                       jsonPath("$.status").value("BAD_REQUEST"),
                       jsonPath("$.message").value("사용자 아이디 값은 양수여야 합니다.")
               );
    }

    @DisplayName("유효하지 않은 상품 아이디의 경우 주문을 실패한다.")
    @Test
    void positiveProductIdCreateOrder() throws Exception {
        // given
        long userId = 1;

        int orderCount = 1;
        long productId = -1;
        OrderPaymentDTO orderPayment1 = createOrderPaymentDTO(orderCount, productId);

        OrderRequest request = OrderRequest.builder()
                                                 .userId(userId)
                                                 .orderItems(List.of(orderPayment1))
                                                 .build();

        mockMvc.perform(
                       post("/orders")
                               .content(objectMapper.writeValueAsString(request))
                               .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                       status().isBadRequest(),
                       jsonPath("$.code").value("400"),
                       jsonPath("$.status").value("BAD_REQUEST"),
                       jsonPath("$.message").value("상품 아이디는 양수여야 합니다.")
               );
    }

    @DisplayName("유효하지 않은 주문 갯수인 경우 주문을 실패한다.")
    @Test
    void positiveOrderCountCreateOrder() throws Exception {
        // given
        long userId = 1;

        int orderCount = -1;
        long productId = 1;
        OrderPaymentDTO orderPayment1 = createOrderPaymentDTO(orderCount, productId);

        OrderRequest request = OrderRequest.builder()
                                                 .userId(userId)
                                                 .orderItems(List.of(orderPayment1))
                                                 .build();

        mockMvc.perform(
                       post("/orders")
                               .content(objectMapper.writeValueAsString(request))
                               .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                       status().isBadRequest(),
                       jsonPath("$.code").value("400"),
                       jsonPath("$.status").value("BAD_REQUEST"),
                       jsonPath("$.message").value("상품 주문 갯수는 양수여야 합니다.")
               );
    }

    @DisplayName("상품 주문에 성공한다.")
    @Test
    void createOrder() throws Exception {
        // given
        long userId = 1;
        String userName = "박지용";

        long orderId = 1;

        OrderPaymentDTO orderPayment1 = createOrderPaymentDTO(1, 1);
        OrderPaymentDTO orderPayment2 = createOrderPaymentDTO(1, 2);

        OrderRequest request = OrderRequest.builder()
                                                 .userId(userId)
                                                 .orderItems(List.of(orderPayment1, orderPayment2))
                                                 .build();

//        given(orderService.createOrder(any())).willReturn(createOrderEntity(orderId, userId));
//
//        mockMvc.perform(
//                   post("/orders")
//                       .content(objectMapper.writeValueAsString(request))
//                       .contentType(MediaType.APPLICATION_JSON))
//               .andDo(print())
//               .andExpectAll(
//                   status().isOk(),
//                   jsonPath("$.code").value("200"),
//                   jsonPath("$.status").value("OK"),
//                   jsonPath("$.message").value("OK"),
//                   jsonPath("$.data.order.id").value(orderId),
//                   jsonPath("$.data.order.userId").value(userId),
//                   jsonPath("$.data.order.orderItems").isArray()
//               );
    }

    private OrderPaymentDTO createOrderPaymentDTO(int orderCount, long productId) {
        return OrderPaymentDTO.builder()
                              .orderCount(orderCount)
                              .productId(productId)
                              .build();
    }

    private Order createOrderEntity(long orderId, long userId) {
//        User user = new User(userId, null, null);
//        return new Order(orderId, user, State.COMPLETED, List.of());
        return null;
    }
}