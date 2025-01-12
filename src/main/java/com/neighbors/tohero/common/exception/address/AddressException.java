package com.neighbors.tohero.common.exception.address;

import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import lombok.Getter;

@Getter
public class AddressException extends RuntimeException{
    private final BaseResponseStatus status;
    private final String message;

    public AddressException(BaseResponseStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
