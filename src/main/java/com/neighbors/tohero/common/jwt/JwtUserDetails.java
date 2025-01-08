package com.neighbors.tohero.common.jwt;

import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.domain.domain.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class JwtUserDetails {
    private final String email;
    private final String nickname;
    private final Role role;
    private final long userId;

    public static JwtUserDetails from(User user) {
        return JwtUserDetails.builder()
                .email(user.getEmail())
                .nickname(user.getUserName())
                .role(user.getRole())
                .userId(user.getUserId())
                .build();
    }

    public static JwtUserDetails makeGuestJwtDetails(String nickname) {
        return JwtUserDetails.builder()
                .role(Role.GUEST)
                .nickname(nickname)
                .build();
    }
}
