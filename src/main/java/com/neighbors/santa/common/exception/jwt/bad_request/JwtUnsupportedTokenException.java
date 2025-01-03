package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnsupportedTokenException extends JwtBadRequestException {

    private final BaseResponseStatus status;
    private final String message;

    public JwtUnsupportedTokenException(BaseResponseStatus status, String message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}
