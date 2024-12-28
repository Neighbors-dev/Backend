package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtUnsupportedTokenException extends JwtBadRequestException {

    private final BaseResponseStatus status;
    private final BaseResponseMessage message;

    public JwtUnsupportedTokenException(BaseResponseStatus status, BaseResponseMessage message) {
        super(status, message);
        this.status = status;
        this.message = message;
    }
}
