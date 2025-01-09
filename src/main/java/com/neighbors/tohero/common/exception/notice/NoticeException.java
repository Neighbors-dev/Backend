package com.neighbors.tohero.common.exception.notice;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;

public class NoticeException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public NoticeException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}