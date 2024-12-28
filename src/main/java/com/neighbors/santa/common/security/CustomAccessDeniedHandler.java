package com.neighbors.santa.common.security;

import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import com.neighbors.santa.common.ErrorResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("JwtAuthenticationEntryPoint : enter CustomAccessDeniedHandler ");

        ErrorResponseUtil.setResponse(response, BaseResponseStatus.JWT_ERROR);
    }
}
