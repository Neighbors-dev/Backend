package com.neighbors.tohero.presentation.exception_handler;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.address.AddressException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AddressException.class)
    public ResponseEntity<BaseResponse> handle_NoticeException(AddressException e) {
        BaseResponse response = new BaseResponse(
                e.getStatus(),
                e.getMessage()
        );

        return ResponseEntity.badRequest()
                .body(response);
    }
}
