package com.neighbors.tohero.common.exception.letter;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;

public class LetterException extends RuntimeException {

    private final BaseResponseStatus status;
    private final String message;

    public LetterException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
