package com.neighbors.tohero.common.exception.jwt.unauthorized;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnauthorizedTokenException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtUnauthorizedTokenException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
