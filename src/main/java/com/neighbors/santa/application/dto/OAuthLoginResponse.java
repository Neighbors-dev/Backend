package com.neighbors.santa.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.santa.common.jwt.AuthTokens;
import lombok.Builder;

@Builder
public class OAuthLoginResponse {
    private boolean isMember;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AuthTokens authTokens;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    public static OAuthLoginResponse createSuccessObjFrom(AuthTokens authTokens, String email) {
        return OAuthLoginResponse.builder()
                .authTokens(authTokens)
                .email(email)
                .isMember(true)
                .build();
    }
}
