package com.hhplus.commerce.spring.user.domain.service;

import com.hhplus.commerce.spring.user.domain.model.User;
import com.hhplus.commerce.spring.user.infrastructure.jwt.UserDetailsImpl;
import com.hhplus.commerce.spring.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException(username + " not found."));

        return UserDetailsImpl.of(user);
    }
}
