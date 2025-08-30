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
        Optional<User> optional = findUser(command.getEmail());
        if (optional.isPresent()) throw new CustomConflictException(ConflictErrorCode.DUPLICATE_EMAIL, command.getEmail());

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

    private User getUser(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new CustomNotFoundException(NotFoundErrorCode.USER_NOT_FOUNT));
    }

    private Optional<User> findUser(String email) {
        return userRepository.findByEmailAddress(email);
    }
}
