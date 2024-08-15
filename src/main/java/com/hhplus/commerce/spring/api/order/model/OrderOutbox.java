package com.hhplus.commerce.spring.api.order.model;

import com.hhplus.commerce.spring.api.common.infrastructure.database.BaseEntity;
import com.hhplus.commerce.spring.api.order.model.convert.AggregateTypeConverter;
import com.hhplus.commerce.spring.api.order.model.convert.EventStatusConverter;
import com.hhplus.commerce.spring.api.order.model.convert.EventTypeConverter;
import com.hhplus.commerce.spring.api.order.model.type.AggregateType;
import com.hhplus.commerce.spring.api.order.model.type.EventStatus;
import com.hhplus.commerce.spring.api.order.model.type.EventType;
import com.hhplus.commerce.spring.api.order.service.request.OrderEvent;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AttributeOverrides({
        /**
         * 공통 BaseTimeEntity의 각 필드를 테이블별로 다른 컬럼명으로 사용하기 위한 설정.
         */
        @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
        @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_order_outbox")
public class OrderOutbox extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "outbox_id", nullable = false)
    private Long id;

    @Column(name = "aggregate_id", nullable = false)
    private Long aggregateId;

    @Convert(converter = AggregateTypeConverter.class)
    @Column(name = "aggregate_type", nullable = false)
    private AggregateType aggregateType;

    @Convert(converter = EventTypeConverter.class)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(name = "payload", nullable = false)
    private String payload;

    @Convert(converter = EventStatusConverter.class)
    @Column(name = "event_status", nullable = false)
    private EventStatus eventStatus;

    public OrderOutbox(Long aggregateId, AggregateType aggregateType, EventType eventType, String payload, EventStatus eventStatus) {
        this.aggregateId = aggregateId;
        this.aggregateType = aggregateType;
        this.eventType = eventType;
        this.payload = payload;
        this.eventStatus = eventStatus;
    }

    public void published() {
        this.eventStatus = EventStatus.COMPLETED;
    }

    public void sendUpdate() {
        this.setModifiedDateTime(LocalDateTime.now());
    }
}
