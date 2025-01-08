package com.neighbors.tohero.application.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.domain.domain.user.model.User;

public record AuthenticateUserResponse(
        AuthTokens authTokens,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserInfo userInfo
) {
    public record UserInfo(
            long userId,
            String nickname,
            String email,
            Role role
    ){
    }

    public static AuthenticateUserResponse toUserDTO(AuthTokens authTokens, User user) {
        UserInfo userInfo  = new UserInfo(user.getUserId(), user.getUserName(), user.getEmail(), user.getRole());
        return new AuthenticateUserResponse(authTokens, userInfo);
    }

    public static AuthenticateUserResponse toGuestDTO(AuthTokens authTokens) {
        return new AuthenticateUserResponse(authTokens, null);
    }
}
