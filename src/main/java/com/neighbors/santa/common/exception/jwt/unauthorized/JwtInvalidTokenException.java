package com.neighbors.santa.common.exception.jwt.unauthorized;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtInvalidTokenException extends JwtUnauthorizedTokenException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtInvalidTokenException(BaseResponseStatus status, String message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}
