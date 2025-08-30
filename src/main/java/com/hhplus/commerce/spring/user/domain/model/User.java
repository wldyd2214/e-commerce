package com.hhplus.commerce.spring.user.domain.model;

import com.hhplus.commerce.spring.common.domain.entity.BaseEntity;
import com.hhplus.commerce.spring.common.domain.event.DomainEvents;
import com.hhplus.commerce.spring.common.exception.CustomBadRequestException;
import com.hhplus.commerce.spring.common.exception.code.BadRequestErrorCode;
import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.event.UserCreatedEvent;
import com.hhplus.commerce.spring.user.domain.model.type.UserStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.springframework.security.crypto.password.PasswordEncoder;

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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "point", nullable = false, precision = 19, scale = 2)
    private BigDecimal point;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private UserStatus status;

    @Version
    private Long version;

    @Builder(access = AccessLevel.PRIVATE)
    private User(String email, String passwordHash, String name, BigDecimal point, UserStatus status, Long version) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.point = point;
        this.status = status;
        this.version = version;
    }

    /**
     * 회원 엔티티 생성
     * @param command
     * @return
     */
    public static User create(UserCommand.Register command, PasswordEncoder passwordEncoder) {
        // 회원 엔티티 생성
        User user = User.builder()
            .email(command.getEmail())
            .passwordHash(passwordEncoder.encode(command.getPassword()))
            .name(command.getName())
            .point(BigDecimal.ZERO)
            .status(UserStatus.ACTIVE)
            .build();

        // 회원 생성 이벤트 발행
        DomainEvents.raise(UserCreatedEvent.of(user.getEmail()));

        // 회원 엔티티 리턴
        return user;
    }

    /**
     * 비밀번호 일치 여부 확인
     * @param password
     * @param passwordEncoder
     * @return
     */
    public boolean verifyPassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, passwordHash);
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