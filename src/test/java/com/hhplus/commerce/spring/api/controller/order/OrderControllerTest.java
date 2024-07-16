package com.hhplus.commerce.spring.api.controller.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.order.controller.OrderController;
import com.hhplus.commerce.spring.api.order.controller.dto.OrderPaymentDTO;
import com.hhplus.commerce.spring.api.order.controller.request.CreateOrderRequest;
import com.hhplus.commerce.spring.api.order.service.OrderService;
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

    @MockBean
    protected OrderService orderService;

    @DisplayName("상품 주문/결제에 성공한다.")
    @Test
    void orderPayment() throws Exception {
        // given
        long userId = 1;

        OrderPaymentDTO orderPayment1 = createOrderPaymentDTO(1, 1);
        OrderPaymentDTO orderPayment2 = createOrderPaymentDTO(1, 2);

        CreateOrderRequest request = CreateOrderRequest.builder()
                                                       .userId(userId)
                                                       .orderItems(List.of(orderPayment1, orderPayment2))
                                                       .build();

        mockMvc.perform(
                   post("/orders/payment")
                       .content(objectMapper.writeValueAsString(request))
                       .contentType(MediaType.APPLICATION_JSON))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value("200"),
                   jsonPath("$.status").value("OK"),
                   jsonPath("$.message").value("OK")
               );
    }

    private OrderPaymentDTO createOrderPaymentDTO(int orderCount, long productId) {
        return OrderPaymentDTO.builder()
                              .orderCount(orderCount)
                              .productId(productId)
                              .build();
    }
}