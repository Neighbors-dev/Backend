package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.login.service.OAuthService;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class OAuthController {

    private final OAuthService oAuthService;

    @Operation(summary = "카카오 로그인 API", description = "카카오톡으로 로그인하기 API입니다.")
    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<BaseResponse> kakaoLogin(@RequestParam("code") String code) {
        return ResponseEntity.ok()
                .body(oAuthService.oAuthKaKaoLoin(code));
    }
}
