package com.neighbors.tohero.common.exception.user;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;

public class UserException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public UserException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
