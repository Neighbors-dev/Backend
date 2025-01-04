package com.neighbors.tohero.application.login.service;

import com.neighbors.tohero.application.login.dto.kakao.KakaoInfoResponse;
import com.neighbors.tohero.application.login.dto.OAuthLoginResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.login.model.User;
import com.neighbors.tohero.domain.domain.login.service.CreateUser;
import com.neighbors.tohero.domain.domain.login.service.oauth.kakao.RequestKakaoInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RequestKakaoInfo requestUserInfo;
    private final CreateUser createUser;
    private final JwtProvider jwtProvider;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirect_uri;

    @Value("${oauth.kakao.redirect-client}")
    private String redirect_uri_client;


    public BaseResponse<OAuthLoginResponse> oAuthKaKaoLoin(String code){
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo.requestKakaoInfo(code, redirect_uri);
        return afterAuthorizedOauth(kakaoInfoResponse);
    }

    public BaseResponse<OAuthLoginResponse> oAuthKaKaoLoinLocal(String code){
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo.requestKakaoInfo(code, redirect_uri_client);
        return afterAuthorizedOauth(kakaoInfoResponse);
    }

    private BaseResponse<OAuthLoginResponse> afterAuthorizedOauth(KakaoInfoResponse kakaoInfoResponse){
        User user = User.builder()
                .userName(kakaoInfoResponse.getNickname())
                .email(kakaoInfoResponse.getEmail())
                .role(Role.USER)
                .build();

        User createdUser = createUser.createUser(user);
        AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.from(createdUser));

        return new BaseResponse<>(
            BaseResponseStatus.OK,
            BaseResponseMessage.로그인_성공했습니다.getMessage(),
            OAuthLoginResponse.createSuccessObjFrom(authTokens, kakaoInfoResponse.getEmail())
        );
    }
}
