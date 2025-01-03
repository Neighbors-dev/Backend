package com.neighbors.santa.application.oauth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neighbors.santa.common.jwt.AuthTokens;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
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
