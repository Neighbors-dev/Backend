package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.auth.service.AuthService;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/refreshToken")
    public ResponseEntity<BaseResponse> refreshAccessToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok()
                .body(authService.reissueToken(refreshToken));
    }

}
