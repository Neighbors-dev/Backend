package com.neighbors.tohero.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void setResponse(HttpServletResponse response, BaseResponseStatus responseStatus) throws IOException {

        BaseResponse errorResponse = new BaseResponse(responseStatus, "JWT TOKEN 오류입니다.");

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
