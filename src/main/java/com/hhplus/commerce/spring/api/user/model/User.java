package com.hhplus.commerce.spring.api.user.model;

import com.hhplus.commerce.spring.api.common.infrasture.database.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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

    public Integer pointCharge(int chargeAmount) {
        return this.userPoint += chargeAmount;
    }

    @Builder
    public User(Long id, String userName, Integer userPoint) {
        this.id = id;
        this.userName = userName;
        this.userPoint = userPoint;
    }

    public void UserPointDeduction(int pointDeduction) {
        if (this.userPoint < pointDeduction) {
            throw new IllegalArgumentException("사용자 잔액 부족");
        }
        this.userPoint = this.userPoint - pointDeduction;
    }
}
