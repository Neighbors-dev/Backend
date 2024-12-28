package com.neighbors.santa.presentation;

import com.neighbors.santa.application.dto.OAuthLoginResponse;
import com.neighbors.santa.application.service.OAuthService;
import com.neighbors.santa.application.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<BaseResponse<OAuthLoginResponse>> kakaoLogin(@RequestParam("code") String code) {
        return ResponseEntity.ok()
                .body(oAuthService.oAuthLoin(code));
    }
}
