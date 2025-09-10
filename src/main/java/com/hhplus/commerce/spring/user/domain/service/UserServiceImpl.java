package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.common.exception.CustomConflictException;
import com.hhplus.commerce.spring.common.exception.CustomNotFoundException;
import com.hhplus.commerce.spring.common.exception.code.ConflictErrorCode;
import com.hhplus.commerce.spring.common.exception.code.NotFoundErrorCode;
import com.hhplus.commerce.spring.user.domain.command.UserCommand;
import com.hhplus.commerce.spring.user.domain.dto.UserSummaryInfo;
import com.hhplus.commerce.spring.user.domain.model.User;
import com.hhplus.commerce.spring.user.domain.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    /**
     * 사용자 정보 등록 (회원 가입)
     * @param command
     * @return
     */
    @Override
    @Transactional
    public UserSummaryInfo register(UserCommand.Register command) {
        // 중복 이메일 조회
        validateDuplicateEmail(command.getEmail());

        // 회원 정보 등록
        User user = User.create(command, passwordEncoder);
        userRepository.save(user);

        // 회원 정보 리턴
        return UserSummaryInfo.of(user);
    }

    /**
     * 사용자 포인트 충전
     * @param command
     */
    @Override
    @Transactional
    public UserSummaryInfo chargeUserPoints(UserCommand.ChargePoint command) {
        // 사용자 정보 조회
        User user = getUser(command.getUserId());

        // 포인트 충전
        user.chargePoint(command.getChargePoint());

        // 사용자 포인트 충전 정보 리턴
        return UserSummaryInfo.of(user);
    }

    /**
     * 사용자 ID로 사용자를 조회합니다.
     * 이 메소드는 사용자가 반드시 존재해야 하는 경우에 사용됩니다.
     *
     * @param userId 조회할 사용자의 ID
     * @return User 객체 (null이 아님을 보장)
     * @throws CustomNotFoundException 사용자를 찾을 수 없는 경우
     */
    private User getUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomNotFoundException(NotFoundErrorCode.USER_NOT_FOUNT));
    }

    /**
     * 이메일 주소로 사용자를 조회합니다.
     * 사용자가 존재하지 않을 수도 있는 경우에 사용합니다.
     *
     * @param email 조회할 사용자의 이메일 주소
     * @return 사용자를 찾으면 해당 User 객체를 담은 Optional을,
     *         찾지 못하면 빈 Optional을 반환합니다.
     */
    private Optional<User> findUser(String email) {
        return userRepository.findByEmailAddress(email);
    }

    /**
     * 중복 이메일이 존재하는 경우 예외를 발생시켜야 하는 경우에 사용합니다.
     * @param email
     */
    private void validateDuplicateEmail(String email) {
        findUser(email).ifPresent(user -> {
            throw new CustomConflictException(ConflictErrorCode.DUPLICATE_EMAIL, email);
        });
    }
}
