package com.hhplus.commerce.spring.presentation.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.hhplus.commerce.spring.old.application.order.OrderFacade;
import com.hhplus.commerce.spring.old.domain.product.model.Product;
import com.hhplus.commerce.spring.old.domain.product.repository.ProductCommandRepository;
import com.hhplus.commerce.spring.old.domain.user.entity.User;
import com.hhplus.commerce.spring.old.infrastructure.user.repository.UserCommandRepositoryImpl;
import com.hhplus.commerce.spring.old.presentation.order.dto.request.OrderItemRequest;
import com.hhplus.commerce.spring.old.presentation.order.dto.request.OrderRequest;
import com.hhplus.commerce.spring.old.presentation.order.mapper.OrderRequestMapper;
import com.hhplus.commerce.spring.old.presentation.order.mapper.OrderResponseMapper;
import com.hhplus.commerce.spring.support.ControllerIntegrationTestSupport;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class OrderControllerIntegrationTest extends ControllerIntegrationTestSupport {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderRequestMapper requestMapper;
    @Autowired
    private OrderResponseMapper responseMapper;

    @Autowired
    private UserCommandRepositoryImpl userCommandRepository;
    @Autowired
    private ProductCommandRepository productCommandRepository;

    String productNameFormat = "테스트 %d번 상품";
    String productDescFormat = "테스트 %d번 상품 입니다.";
    Integer price = 100000;
    Integer count = 100;
    int minSize = 1;
    int maxSize = 16;

    List<Product> products = new ArrayList<>();
    User user = User.createUser("주지훈", new BigDecimal(1000000));

    /**
     * 테스트 데이터 준비
     */
    @BeforeEach
    void setUp() {

        // 1. 사용자 등록
        userCommandRepository.save(user);

        // 2. 상품 등록
        products = createProducts();
        productCommandRepository.saveAll(products);
    }

    private List<Product> createProducts() {

        List<Product> saveProducts = new ArrayList<>();

        for (int i = minSize; i < maxSize; i++) {
            saveProducts.add(
                Product.createProduct(
                    String.format(productNameFormat, i),
                    String.format(productDescFormat, i),
                    price,
                    count
                )
            );
        }

        return saveProducts;
    }

    @DisplayName("주문 생성 - 유효하지 않은 사용자 주문")
    @Test
    void createOrderPositiveUser() {
    }

    @DisplayName("주문 생성 - 유효하지 않은 상품 주문")
    @Test
    void createOrderPositiveProduct() {
    }

    @DisplayName("주문 생성 - 유효하지 상품 주문 갯수")
    @Test
    void createOrderPositiveCount() {
    }

    @DisplayName("주문 생성 - 성공")
    @Test
    void createOrder() throws Exception {

        int orderCount = 2;
        Product product = products.stream().findFirst().get();

        // 주문 아이템 생성
        List<OrderItemRequest> orderItems = new ArrayList<>();
        orderItems.add(new OrderItemRequest(product.getId(), orderCount));

        // 주문 요청 생성
        OrderRequest requestBody = new OrderRequest(user.getId(), orderItems);

        mockMvc.perform(
                    post("/orders").content(objectMapper.writeValueAsString(requestBody))
                                              .contentType(MediaType.APPLICATION_JSON)
               )
               .andDo(print())
               .andExpectAll(
                   status().isOk(),
                   jsonPath("$.code").value(HttpStatus.OK.value()),
                   jsonPath("$.status").value(HttpStatus.OK.name()),
                   jsonPath("$.message").value(HttpStatus.OK.name()),
                   jsonPath("$.data.orderItems").isNotEmpty()
               );
    }
}