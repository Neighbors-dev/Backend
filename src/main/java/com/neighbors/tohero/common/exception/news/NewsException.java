package com.neighbors.tohero.common.exception.news;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class NewsException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public NewsException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
