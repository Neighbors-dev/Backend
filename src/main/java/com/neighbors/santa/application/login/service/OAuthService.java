package com.neighbors.santa.application.login.service;

import com.neighbors.santa.application.login.dto.kakao.KakaoInfoResponse;
import com.neighbors.santa.application.login.dto.OAuthLoginResponse;
import com.neighbors.santa.application.baseResponse.BaseResponse;
import com.neighbors.santa.application.baseResponse.BaseResponseMessage;
import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
import com.neighbors.santa.common.jwt.AuthTokens;
import com.neighbors.santa.common.jwt.JwtProvider;
import com.neighbors.santa.common.jwt.JwtUserDetails;
import com.neighbors.santa.domain.login.model.User;
import com.neighbors.santa.domain.login.service.CreateUser;
import com.neighbors.santa.domain.login.service.oauth.kakao.RequestKakaoInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {

    private final RequestKakaoInfo requestUserInfo;
    private final CreateUser createUser;
    private final JwtProvider jwtProvider;

    public BaseResponse<OAuthLoginResponse> oAuthKaKaoLoin(String code){
        KakaoInfoResponse kakaoInfoResponse = requestUserInfo.requestKakaoInfo(code);

        User user = User.builder()
                .userName(kakaoInfoResponse.getNickname())
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
}
