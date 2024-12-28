package com.neighbors.santa.presentation;

import com.neighbors.santa.application.OAuthService;
import com.neighbors.santa.application.dto.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OAuthController {

    private final OAuthService oAuthService;

    @GetMapping("/oauth/kakao/callback")
    public ResponseEntity<BaseResponse> kakaoLogin(@RequestParam("code") String code) {
        return ResponseEntity.ok()
                .body(oAuthService.oAuthLoin(code));
    }
}
