package com.hhplus.commerce.spring.api.user.model;

import static com.hhplus.commerce.spring.api.common.presentation.exception.code.BadRequestErrorCode.INSUFFICIENT_BALANCE;

import com.hhplus.commerce.spring.api.common.infrastructure.database.BaseEntity;
import com.hhplus.commerce.spring.api.common.presentation.exception.CustomBadRequestException;

import jakarta.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "user_balance_amount", nullable = false)
    private Integer userPoint;

    @Version
    private Long version;

    public Integer pointCharge(int chargeAmount) {
        return this.userPoint += chargeAmount;
    }

    @Builder
    public User(Long id, String userName, Integer userPoint) {
        this.id = id;
        this.userName = userName;
        this.userPoint = userPoint;
    }

    public void deductUserPoint(int deductionPoint) {
        if (this.userPoint < deductionPoint) {
            throw new CustomBadRequestException(INSUFFICIENT_BALANCE);
        }
        this.userPoint = this.userPoint - deductionPoint;
    }
}
