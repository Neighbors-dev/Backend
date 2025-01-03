package com.neighbors.santa.presentation;

import com.neighbors.santa.application.oauth.dto.OAuthLoginResponse;
import com.neighbors.santa.application.oauth.service.OAuthService;
import com.neighbors.santa.application.baseResponse.BaseResponse;
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
                .body(oAuthService.oAuthKaKaoLoin(code));
    }
}
