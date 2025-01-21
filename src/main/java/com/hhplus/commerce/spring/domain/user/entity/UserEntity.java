package com.hhplus.commerce.spring.domain.user.entity;

import com.hhplus.commerce.spring.domain.common.model.BaseEntity;
import com.hhplus.commerce.spring.presentation.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.presentation.common.exception.code.BadRequestErrorCode;
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
public class UserEntity extends BaseEntity {

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

    public UserEntity(String name, BigDecimal point) {
        this.name = name;
        this.point = point;
    }

    public UserEntity(Long id, String name, BigDecimal point) {
        this.id = id;
        this.name = name;
        this.point = point;
    }

    public BigDecimal chargePoint(BigDecimal point) {

        if (point.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("충전 액수는 0보다 커야 합니다.");
        }

        this.point = this.point.add(point);

        return this.point;
    }

    public BigDecimal deductPoint(BigDecimal point) {

        if (this.point.compareTo(point) < 0) {
            throw new CustomBadRequestException(BadRequestErrorCode.USER_POINT_BAD_REQUEST);
        }

        return this.point.subtract(point);
    }
}