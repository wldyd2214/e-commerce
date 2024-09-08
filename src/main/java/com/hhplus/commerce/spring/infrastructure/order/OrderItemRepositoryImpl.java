package com.hhplus.commerce.spring.infrastructure.order;

import static com.hhplus.commerce.spring.domain.order.model.QOrder.order;
import static com.hhplus.commerce.spring.domain.order.model.QOrderItem.orderItem;

import com.hhplus.commerce.spring.domain.order.model.OrderItem;
import com.hhplus.commerce.spring.domain.order.model.type.OrderStatus;
import com.hhplus.commerce.spring.domain.order.repository.OrderItemRepository;
import com.hhplus.commerce.spring.old.api.product.model.Product;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Repository
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemJPARepository jpaRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderItem> saveAll(List<OrderItem> orderItems) {
        return jpaRepository.saveAll(orderItems);
    }

    @Override
    public List<Product> selectPopularOrderItems() {

        // TODO: 쿼리
        /*
           SELECT oi.product_id, SUM(oi.order_product_count) as order_product_count
           FROM tb_order_item oi
           JOIN tb_order o ON oi.order_id = o.order_id
           WHERE o.order_status = "COMPLETED" AND oi.reg_date >= CURDATE() - INTERVAL 3 DAY
           GROUP BY oi.product_id
           ORDER BY order_product_count DESC
           LIMIT 5;
         */


        // TODO: 인덱스 추가
        /*
        // 쿼리에서 최근 3일간의 데이터를 필터링하는데 사용되어 조회 속도를 향상시키기 위해 인덱스 추가
        CREATE INDEX idx_order_date ON order_item(reg_date);

        // 쿼리에서 그룹과 집계에 사용되어 조회 속도를 향상시키기 위해 인덱스 추가
        CREATE INDEX idx_product_id ON order_item(product_id);

        // reg_date, product_id 컬럼에 대한 복한 인덱스를 추가하여 속도를 향상
        CREATE INDEX idx_order_date_product_id ON order_item(reg_date, product_id);
        */

        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);

        long startTime = System.currentTimeMillis();

        List<Tuple> tuples =
                jpaQueryFactory
                        .select(orderItem.product, orderItem.orderProductCount.sum())
                        .from(orderItem)
                        .join(orderItem.order, order)
                        .where(order.orderStatus.eq(OrderStatus.COMPLETED), orderItem.createdDateTime.after(threeDaysAgo.atStartOfDay()))
                        .groupBy(orderItem.product)
                        .orderBy(orderItem.orderProductCount.sum().desc())
                        .limit(5)
                        .fetch();

        long endTime = System.currentTimeMillis();
        log.info("selectPopularOrderItems() - execute time: {}(ms)", endTime - startTime);

        return tuples.stream()
                     .map(t -> t.get(orderItem.product))
                     .collect(Collectors.toList());
    }
}
