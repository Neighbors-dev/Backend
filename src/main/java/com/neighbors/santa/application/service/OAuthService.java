package com.neighbors.santa.application.service;

import com.neighbors.santa.application.dto.KakaoInfoResponse;
import com.neighbors.santa.application.dto.KakaoTokens;
import com.neighbors.santa.application.dto.OAuthLoginResponse;
import com.neighbors.santa.application.dto.response.BaseResponse;
import com.neighbors.santa.application.dto.response.BaseResponseMessage;
import com.neighbors.santa.application.dto.response.BaseResponseStatus;
import com.neighbors.santa.common.jwt.AuthTokens;
import com.neighbors.santa.common.jwt.JwtProvider;
import com.neighbors.santa.common.jwt.JwtUserDetails;
import com.neighbors.santa.domain.model.User;
import com.neighbors.santa.domain.service.CreateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RestTemplate restTemplate;
    private final CreateUser createUser;
    private final JwtProvider jwtProvider;

    public BaseResponse<OAuthLoginResponse> oAuthLoin(String code){
        String accessToken = requestToken(code);
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo(accessToken);

        User user = User.builder()
                .email(kakaoInfoResponse.getEmail())
                .userName(kakaoInfoResponse.getNickname())
                .build();

        createUser.createUser(user);

        AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.from(user));

        return new BaseResponse<>(
            BaseResponseStatus.OK,
            BaseResponseMessage.로그인_성공했습니다.getMessage(),
            OAuthLoginResponse.createSuccessObjFrom(authTokens, kakaoInfoResponse.getEmail())
        );
    }

    private String requestToken(String code) {
        String url = "https://kauth.kakao.com" + "/oauth/token";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "ba2aa0b8b2d94dd49ad6b044e464ed71");
        body.add("redirect_uri", "https://glittery-madeleine-215e2f.netlify.app/callback/kakaotalk");
        body.add("code", code);

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        KakaoTokens response = restTemplate.postForObject(url, request, KakaoTokens.class);

        assert response != null;
        return response.getAccessToken();
    }

    private KakaoInfoResponse requestUserInfo(String accessToken){
        String url = "https://kapi.kakao.com" + "/v2/user/me";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.email\", \"kakao_account.profile\"]");

        HttpEntity<?> request = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(url, request, KakaoInfoResponse.class);
    }
}
