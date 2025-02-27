package com.hhplus.commerce.spring.application.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.hhplus.commerce.spring.application.order.dto.OrderFacadeDTO;
import com.hhplus.commerce.spring.application.order.dto.request.OrderFacadeRequest;
import com.hhplus.commerce.spring.application.order.dto.response.OrderFacadeResponse.Create;
import com.hhplus.commerce.spring.domain.product.entity.Product;
import com.hhplus.commerce.spring.domain.product.repository.ProductCommandRepository;
import com.hhplus.commerce.spring.domain.user.dto.UserCommand;
import com.hhplus.commerce.spring.domain.user.entity.User;
import com.hhplus.commerce.spring.domain.user.repository.UserCommandRepository;
import com.hhplus.commerce.spring.infrastructure.product.repository.ProductJpaRepository;
import com.hhplus.commerce.spring.old.api.product.repository.ProductRepository;
import com.hhplus.commerce.spring.support.IntegrationTestSupport;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderFacadeIntegrationTest extends IntegrationTestSupport {

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    UserCommandRepository userCommandRepository;
    @Autowired
    ProductCommandRepository productCommandRepository;

    @Autowired
    ProductJpaRepository jpaRepository;

    Product product = Product.createProduct("자쿰의 투구", "정옵 자쿰의 투구", 100000, 10);

    @BeforeEach
    void setUp() {
        // 1. 상품 정보 저장
        productCommandRepository.saveAll(List.of(product));
    }

    /**
     * 여러 사용자가 동시에 하나의 상품의 주문을 요청한다!
     */
    @DisplayName("주문 생성 - 동시성 테스트")
    @Test
    void concurrentOrderCreation() {

        int numberOfThreads = 1; // 동시에 실행할 스레드 수

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<CompletableFuture<Create>> futures = IntStream.range(0, numberOfThreads)
            .mapToObj(i -> CompletableFuture.supplyAsync(() -> {
                User user = createUser(i);
                OrderFacadeDTO orderFacadeDTO = new OrderFacadeDTO(product.getId(), 2);
                OrderFacadeRequest.Create request = new OrderFacadeRequest.Create(user.getId(), List.of(orderFacadeDTO));
                return orderFacade.orderCreate(request);
            }, executorService))
            .toList();

        // 모든 Future 완료 대기
        List<Create> results = futures.stream()
            .map(CompletableFuture::join)
            .toList();

        // 모든 결과가 성공(true)인지 검증
        assertThat(product).extracting("id", "count")
            .containsExactlyInAnyOrder(tuple(product.getId(), 0));

        executorService.shutdown();
    }

    private User createUser(int i) {
        String userName = String.format("%s번 유저", i);
        BigDecimal point = new BigDecimal("1000000");

        User user = User.createUser(userName, point);
        userCommandRepository.save(user);

        return user;
    }
}
