package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import lombok.Getter;

@Getter
public class JwtBadRequestException extends RuntimeException {

    private final BaseResponseStatus status;
    private final BaseResponseMessage message;

    public JwtBadRequestException(BaseResponseStatus status, BaseResponseMessage message) {
        super(message.getMessage());
        this.status = status;
        this.message = message;
    }
}
