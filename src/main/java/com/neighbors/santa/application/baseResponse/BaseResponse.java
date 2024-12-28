package com.neighbors.santa.application.baseResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"code", "message", "data"})
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

    public BaseResponse(BaseResponseStatus status, String message) {
        this(status, message, null);
    }
}
