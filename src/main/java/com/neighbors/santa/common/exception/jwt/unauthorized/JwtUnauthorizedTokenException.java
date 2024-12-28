package com.neighbors.santa.common.exception.jwt.unauthorized;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnauthorizedTokenException extends RuntimeException {

    private final BaseResponseStatus status;
    private final BaseResponseMessage message;

    public JwtUnauthorizedTokenException(BaseResponseStatus status, BaseResponseMessage message) {
        super(message.getMessage());
        this.status = status;
        this.message = message;
    }
}
