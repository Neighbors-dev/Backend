package com.neighbors.tohero.domain.domain.login.service.oauth.kakao;

import com.neighbors.tohero.application.login.dto.kakao.KakaoInfoResponse;
import com.neighbors.tohero.application.login.dto.kakao.KakaoTokens;
import com.neighbors.tohero.common.annotaion.DomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@DomainService
@RequiredArgsConstructor
public class RequestKakaoInfo {

    @Value("${oauth.kakao.token-url}")
    private String tokenUrl;

    @Value("${oauth.kakao.userInfo-url}")
    private String userInfoUrl;

    @Value("${oauth.kakao.client-key}")
    private String clientKey;

    private final RestTemplate restTemplate;

    public KakaoInfoResponse requestKakaoInfo(String authorizationCode, String redirect_uri){
        String kakaoAccessToken = requestToken(authorizationCode, redirect_uri);
        return requestUserInfo(kakaoAccessToken);
    }

    private String requestToken(String code, String redirect_uri) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = createRequestBodyWithRedirectUrl(code, redirect_uri);

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

    private MultiValueMap<String, String> createRequestBodyWithRedirectUrl(String code, String redirect_uri){
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", clientKey);
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);

        return body;
    }
}
