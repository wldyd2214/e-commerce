package com.hhplus.commerce.spring.presentation.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.commerce.spring.domain.product.service.ProductService;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductRequestMapper;
import com.hhplus.commerce.spring.presentation.product.mapper.ProductResponseMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
class ProductControllerIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRequestMapper requestMapper;
    @Autowired
    ProductResponseMapper responseMapper;

    @DisplayName("상품 목록 조회 - 성공")
    @Test
    void getProducts() throws Exception {
        // when // then
        mockMvc.perform(get("/products"))
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value(HttpStatus.OK.value()),
                   jsonPath("$.status").value(HttpStatus.OK.name()),
                   jsonPath("$.message").value(HttpStatus.OK.name()),
                   jsonPath("$.data").isNotEmpty()
               );
    }

    /*
     * TODO: 테스트시 레디스 데이터 역직렬화 에러 조치 필요!
     */
    @DisplayName("상위 상품 목록 조회 - 성공")
    @Test
    void getProductPopulars() throws Exception {
        // when // then
        mockMvc.perform(get("/products/popular"))
               .andDo(print())
               .andExpectAll(
                       status().isOk(),
                       jsonPath("$.code").value(HttpStatus.OK.value()),
                       jsonPath("$.status").value(HttpStatus.OK.name()),
                       jsonPath("$.message").value(HttpStatus.OK.name()),
                       jsonPath("$.data").isNotEmpty()
               );

    }
}