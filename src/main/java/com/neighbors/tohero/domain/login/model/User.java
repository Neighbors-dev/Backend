package com.neighbors.tohero.domain.login.model;

import com.neighbors.tohero.common.enums.Role;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {
    private Long userId;
    private String userName;
    private String email;
    private Role role;
}
