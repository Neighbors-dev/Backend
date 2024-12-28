package com.neighbors.santa.common.exception.jwt.bad_request;

import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
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
