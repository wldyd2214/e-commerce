package com.hhplus.commerce.spring.api.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.old.api.product.controller.ProductOldController;
import com.hhplus.commerce.spring.old.api.product.service.ProductOldService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductOldController.class)
class ProductOldControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductOldService productOldService;

    @DisplayName("상품 목록 정보를 조회에 성공한다.")
    @Test
    void getProducts() throws Exception {
        // given

        // when // then
        mockMvc.perform(get("/products"))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value("200"),
                   jsonPath("$.status").value("OK"),
                   jsonPath("$.message").value("OK"),
                   jsonPath("$.data").isNotEmpty()
               );
    }

    @DisplayName("상위 상품 목록을 조회한다.")
    @Test
    void getProductPopulars() throws Exception {
        // given

        // when // then
        mockMvc.perform(get("/products/popular"))
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       jsonPath("$.code").value("200"),
                       jsonPath("$.status").value("OK"),
                       jsonPath("$.message").value("OK"),
                       jsonPath("$.data").isNotEmpty()
               );

    }
}