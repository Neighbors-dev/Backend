package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
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
