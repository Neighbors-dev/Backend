package com.neighbors.tohero.application.login.service;

import com.neighbors.tohero.application.login.dto.kakao.KakaoInfoResponse;
import com.neighbors.tohero.application.login.dto.OAuthLoginResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.user.UserException;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.domain.login.service.oauth.kakao.RequestKakaoInfo;
import com.neighbors.tohero.domain.domain.user.service.GetUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RequestKakaoInfo requestUserInfo;
    private final GetUser getUser;
    private final JwtProvider jwtProvider;

    @Value("${oauth.kakao.redirect-uri}")
    private String redirect_uri;

    @Value("${oauth.kakao.redirect-client}")
    private String redirect_uri_client;


    public BaseResponse<OAuthLoginResponse> oAuthKaKaoLoin(String code){
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo.requestKakaoInfo(code, redirect_uri);
        return makeOauthResponseDependingOnExist(kakaoInfoResponse);
    }

    public BaseResponse<OAuthLoginResponse> oAuthKaKaoLoinLocal(String code){
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo.requestKakaoInfo(code, redirect_uri_client);
        return makeOauthResponseDependingOnExist(kakaoInfoResponse);
    }

    private BaseResponse<OAuthLoginResponse> makeOauthResponseDependingOnExist(KakaoInfoResponse kakaoInfoResponse){
        User matchedUser = null;
        AuthTokens authTokens = null;
        try{
            matchedUser = getUser.getUserByEmail(kakaoInfoResponse.getEmail());
            authTokens = jwtProvider.createToken(JwtUserDetails.from(matchedUser));
        }catch(UserException e){
            log.error(e.getMessage());
            return returnNonUserResponse(kakaoInfoResponse.getEmail());
        }

        return new BaseResponse<>(
            BaseResponseStatus.OK,
            BaseResponseMessage.로그인_성공했습니다.getMessage(),
            OAuthLoginResponse.createExistUserResponse(matchedUser,authTokens)
        );
    }

    private BaseResponse<OAuthLoginResponse> returnNonUserResponse(String email){
        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.존재하지_않는_유저입니다.getMessage(),
                OAuthLoginResponse.createNonUserResponse(email)
        );
    }
}
