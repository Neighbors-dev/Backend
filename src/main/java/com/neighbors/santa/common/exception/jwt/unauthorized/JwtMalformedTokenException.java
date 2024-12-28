package com.neighbors.santa.common.exception.jwt.unauthorized;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtMalformedTokenException extends JwtUnauthorizedTokenException {

    private final BaseResponseStatus status;
    private final BaseResponseMessage message;

    public JwtMalformedTokenException(BaseResponseStatus status, BaseResponseMessage message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}