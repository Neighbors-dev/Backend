package com.neighbors.tohero.common.exception.jwt.bad_request;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtNoTokenException extends JwtBadRequestException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtNoTokenException(BaseResponseStatus status, String message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}
