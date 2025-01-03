package com.neighbors.santa.common.jwt;

import com.neighbors.santa.common.enums.Role;
import com.neighbors.santa.domain.login.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class JwtUserDetails {
    private final String email;
    private final Long userId;
    private final Role role;

    public static JwtUserDetails from(User user) {
        return JwtUserDetails.builder()
                .email(user.getEmail())
                .userId(user.getUserId())
                .role(user.getRole())
                .build();
    }

    public static JwtUserDetails makeGuestJwtDetails() {
        return JwtUserDetails.builder()
                .role(Role.GUEST)
                .build();
    }
}
