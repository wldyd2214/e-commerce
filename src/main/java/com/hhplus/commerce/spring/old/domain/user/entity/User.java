package com.hhplus.commerce.spring.old.domain.user.entity;

import com.hhplus.commerce.spring.old.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.old.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.old.presentation.common.exception.code.BadRequestErrorCode;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AttributeOverrides({
        /**
         * 공통 BaseTimeEntity의 각 필드를 테이블별로 다른 컬럼명으로 사용하기 위한 설정.
         */
        @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
        @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_balance_amount", nullable = false)
    private BigDecimal point;

    @Version
    private Long version;

    public User(String name, BigDecimal point) {
        this.name = name;
        this.point = point;
    }

    public User(Long id, String name, BigDecimal point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public static User createUser(String name, BigDecimal point) {
        return new User(name, point);
    }

    public BigDecimal chargePoint(BigDecimal point) {

        if (point.compareTo(BigDecimal.ZERO) < 0) {
            throw new CustomBadRequestException(BadRequestErrorCode.POSITIVE_POINT_BAD_REQUEST);
        }

        this.point = this.point.add(point);

        return this.point;
    }

    public BigDecimal deductPoint(BigDecimal deductionPoints) {

        if (this.point.compareTo(deductionPoints) < 0) {
            throw new CustomBadRequestException(BadRequestErrorCode.USER_INSUFFICIENT_BALANCE_BAD_REQUEST);
        }

        this.point = this.point.subtract(deductionPoints);

        return this.point;
    }
}