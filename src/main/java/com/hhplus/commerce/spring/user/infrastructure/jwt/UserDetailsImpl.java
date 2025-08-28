package com.hhplus.commerce.spring.user.infrastructure.jwt;

import com.hhplus.commerce.spring.user.domain.entity.User;
import java.util.Collection;
import java.util.Collections;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class UserDetailsImpl implements UserDetails {

    private Long key;
    private String email;
    private String name;
    private String password;

    public static UserDetails of(User user) {
        return UserDetailsImpl.builder()
            .key(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .password(user.getPasswordHash())
            .build();
    }

    public static UserDetailsImpl create(Long key, String email) {
        return UserDetailsImpl.builder()
            .key(key)
            .email(email)
            .build();
    }

    /**
     * 사용자에게 부여된 권한을 반환합니다.
     *
     * @return 권한 컬렉션, 사용자가 권한이 없으면 빈 컬렉션을 반환
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
