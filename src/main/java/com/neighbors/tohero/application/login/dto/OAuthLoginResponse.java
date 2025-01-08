package com.neighbors.tohero.application.login.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.domain.domain.user.model.User;
import lombok.Builder;
import lombok.Data;

public record OAuthLoginResponse (
        boolean isMember,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        UserInfo userInfo,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        AuthTokens authTokens,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email
){
    public record UserInfo(
            long userId,
            String nickname,
            String email,
            Role role
    ){
    }

    public static OAuthLoginResponse createNonUserResponse(String email){
        return new OAuthLoginResponse(false, null, null, email);
    }

    public static OAuthLoginResponse createExistUserResponse(User user, AuthTokens authTokens){
        UserInfo userInfo = new UserInfo(user.getUserId(), user.getUserName(), user.getEmail(), user.getRole());
        return new OAuthLoginResponse(true, userInfo, authTokens,null);
    }
}
