package com.neighbors.tohero.application.login.service;

import com.neighbors.tohero.application.login.dto.kakao.KakaoInfoResponse;
import com.neighbors.tohero.application.login.dto.OAuthLoginResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.exception.user.UserException;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.login.model.User;
import com.neighbors.tohero.domain.login.service.CreateUser;
import com.neighbors.tohero.domain.login.service.oauth.kakao.RequestKakaoInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
                .role(Role.USER)
                .build();

        AuthTokens authTokens = null;
        try {
            User createdUser = createUser.createUser(user);
            authTokens = jwtProvider.createToken(JwtUserDetails.from(createdUser));
        }catch(UserException e){
            log.info(e.getMessage());
        }

        return new BaseResponse<>(
            BaseResponseStatus.OK,
            BaseResponseMessage.로그인_성공했습니다.getMessage(),
            OAuthLoginResponse.createSuccessObjFrom(authTokens, kakaoInfoResponse.getEmail())
        );
    }
}
