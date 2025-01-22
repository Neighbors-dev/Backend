package com.neighbors.tohero.domain.domain.user.model;

import com.neighbors.tohero.application.user.dto.AuthenticateUserRequest;
import com.neighbors.tohero.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class User {
    private Long userId;
    private String userName;
    private String email;
    private Role role;
    private String recommenders;

    public static User of (Long userId, String userName, String email, Role role, String recommenders) {
        return new User(userId, userName, email, role, recommenders);
    }

    public static User toEntity(AuthenticateUserRequest authenticateUserRequest) {
        return User.builder()
                .userName(authenticateUserRequest.nickname())
                .email(authenticateUserRequest.email())
                .role(authenticateUserRequest.role())
                .build();
    }

    public String getRecommenders() {
        if(recommenders == null) return "";
        return recommenders;
    }
}
