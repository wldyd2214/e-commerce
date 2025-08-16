package com.hhplus.commerce.spring.user.domain.entity;

import com.hhplus.commerce.spring.common.domain.entity.BaseEntity;
import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_user")
@AttributeOverrides({
    /**
     * 공통 BaseTime Entity 각 필드를 테이블별로 다른 컬럼명으로 사용하기 위한 설정.
     */
    @AttributeOverride(name = "createdDateTime", column = @Column(name = "reg_date")),
    @AttributeOverride(name = "modifiedDateTime", column = @Column(name = "mod_date"))
})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder(access = AccessLevel.PRIVATE)
    private User(String name, BigDecimal point, Long version) {
        this.name = name;
        this.point = point;
        this.version = version;
    }

    public static User create(String name) {
        return User.builder()
                   .name(name)
                   .point(BigDecimal.ZERO)
                   .build();
    }

    /**
     * 사용자 포인트 충전
     * @param point
     * @return
     */
    public BigDecimal chargePoint(BigDecimal point) {

        if (Objects.isNull(point) || point.compareTo(BigDecimal.ZERO) <= 0) {
            throw new CustomBadRequestException(BadRequestErrorCode.AMOUNT_MUST_BE_POSITIVE);
        }

        this.point = this.point.add(point);

        return this.point;
    }
}