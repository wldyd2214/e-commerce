package com.hhplus.commerce.spring.infrastructure.db;

import com.hhplus.commerce.spring.model.entity.OrderItem;
import com.hhplus.commerce.spring.model.entity.Product;
import com.hhplus.commerce.spring.repository.OrderItemRepository;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.hhplus.commerce.spring.model.entity.QOrderItem.orderItem;

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

        LocalDate threeDaysAgo = LocalDate.now().minusDays(3);

        // 쿼리
//        SELECT product_id, SUM(order_product_count) as order_product_count
//        FROM order_item
//        WHERE reg_date >= CURDATE() - INTERVAL 3 DAY
//        GROUP BY product_id
//        ORDER BY order_product_count DESC
//        LIMIT 5;

        // 인덱스 추가
//        CREATE INDEX idx_order_date ON order_item(reg_date);
//        CREATE INDEX idx_product_id ON order_item(product_id);
//        CREATE INDEX idx_order_date_product_id ON order_item(reg_date, product_id);

        List<Tuple> tuples =
                jpaQueryFactory
                        .select(orderItem.product, orderItem.orderProductCount.sum())
                        .from(orderItem)
                        .where(orderItem.createdDateTime.after(threeDaysAgo.atStartOfDay()))
                        .groupBy(orderItem.product)
                        .orderBy(orderItem.orderProductCount.sum().desc())
                        .limit(5)
                        .fetch();

        return tuples.stream()
                     .map(t -> t.get(orderItem.product))
                     .collect(Collectors.toList());
    }
}