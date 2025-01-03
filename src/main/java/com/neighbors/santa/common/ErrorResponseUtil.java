package com.neighbors.santa.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neighbors.santa.application.baseResponse.BaseResponse;
import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ErrorResponseUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void setResponse(HttpServletResponse response, BaseResponseStatus responseStatus) throws IOException {

        BaseResponse errorResponse = new BaseResponse(responseStatus, "");

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

}
