package com.neighbors.tohero.presentation.exception_handler;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.notice.NoticeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NoticeExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NoticeException.class)
    public ResponseEntity<BaseResponse> handle_NoticeException(NoticeException e) {
        BaseResponse response = new BaseResponse(
                BaseResponseStatus.NO_RESULT,
                e.getMessage()
        );

        return ResponseEntity.badRequest()
                .body(response);
    }
}
