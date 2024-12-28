package com.neighbors.santa.domain.service.oauth.kakao;

import com.neighbors.santa.application.oauth.dto.kakao.KakaoInfoResponse;
import com.neighbors.santa.application.oauth.dto.kakao.KakaoTokens;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RequestKakaoInfo {

    @Value("${oauth.kakao.token-url}")
    private String tokenUrl;

    @Value("${oauth.kakao.userInfo-url}")
    private String userInfoUrl;

    @Value("${oauth.kakao.client-key}")
    private String clientKey;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirect_uri;

    private final RestTemplate restTemplate;

    public KakaoInfoResponse requestKakaoInfo(String authorizationCode){
        String kakaoAccessToken = requestToken(authorizationCode);
        return requestUserInfo(kakaoAccessToken);
    }

    private String requestToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientKey);
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        KakaoTokens response = restTemplate.postForObject(tokenUrl, request, KakaoTokens.class);

        assert response != null;
        return response.getAccessToken();
    }

    private KakaoInfoResponse requestUserInfo(String accessToken){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(userInfoUrl, request, KakaoInfoResponse.class);
    }
}
