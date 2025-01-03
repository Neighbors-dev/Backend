package com.neighbors.tohero.common.exception.jwt.bad_request;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtBadRequestException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtBadRequestException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
