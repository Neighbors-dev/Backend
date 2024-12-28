package com.neighbors.santa.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class OAuthLoginResponse {
    private boolean isMember;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AuthTokens authTokens;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;
}
