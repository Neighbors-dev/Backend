package com.neighbors.tohero.presentation.exception_handler;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import jakarta.annotation.Priority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static com.neighbors.tohero.application.baseResponse.BaseResponseMessage.*;

@Priority(0)
@RestControllerAdvice
public class DTOFieldExceptionControllerAdvice {

    private final Map<String, BaseResponseMessage> fieldErrorMapper;

    public DTOFieldExceptionControllerAdvice() {
        fieldErrorMapper = new HashMap<>();
        fieldErrorMapper.put("nickname", 유저_이름의_길이는_1부터_5까지만_가능합니다);
        fieldErrorMapper.put("searchAddress", 주소_검색_쿼리의_길이는_1부터_50까지만_가능합니다);
        fieldErrorMapper.put("role", ROLE_필드는_필수입력입니다);
        fieldErrorMapper.put("email", 이메일_형식이_올바르지_못합니다);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse> handle_fieldValidationException(MethodArgumentNotValidException e) {
        BaseResponse response = new BaseResponse(
                BaseResponseStatus.BAD_REQUEST,
                fieldErrorMapper.get(e.getBindingResult().getFieldError().getField()).getMessage()
        );

        return ResponseEntity.badRequest()
                .body(response);
    }
}
