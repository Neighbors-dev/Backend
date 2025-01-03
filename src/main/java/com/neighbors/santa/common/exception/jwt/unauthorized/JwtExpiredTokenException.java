package com.neighbors.santa.common.exception.jwt.unauthorized;

import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtExpiredTokenException extends JwtUnauthorizedTokenException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtExpiredTokenException(BaseResponseStatus status, String message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}
