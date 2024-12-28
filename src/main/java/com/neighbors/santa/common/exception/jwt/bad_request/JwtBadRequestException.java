package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
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
