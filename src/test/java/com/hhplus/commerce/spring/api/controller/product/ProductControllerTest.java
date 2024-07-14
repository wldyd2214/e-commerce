package com.hhplus.commerce.spring.api.controller.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.api.service.product.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductService productService;

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
}