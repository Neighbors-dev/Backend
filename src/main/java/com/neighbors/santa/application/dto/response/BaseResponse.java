package com.neighbors.santa.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResponse<T> {
    private final BaseResponseStatus status;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public BaseResponse(BaseResponseStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
