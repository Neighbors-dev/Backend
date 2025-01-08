package com.neighbors.tohero.application.auth.dto;

import com.neighbors.tohero.common.jwt.AuthTokens;

public record ReissueTokenResponse(
        AuthTokens authTokens
) {
}
