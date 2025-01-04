package com.neighbors.tohero.domain.domain.login.model;

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

    public static User of (Long userId, String userName, String email, Role role) {
        return new User(userId, userName, email, role);
    }
}
