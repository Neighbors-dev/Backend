package com.neighbors.tohero.application.auth.service;

import com.neighbors.tohero.application.auth.dto.ReissueTokenResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.domain.user.service.GetUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtProvider jwtProvider;
    private final GetUser getUser;

    public BaseResponse<ReissueTokenResponse> reissueToken(String refreshToken){
        //TODO : Redis에 refreshToken 확인 작업
        jwtProvider.isExpiredToken(refreshToken);
        if(jwtProvider.isGuestToken(refreshToken)){
            String nickname = jwtProvider.getNickname(refreshToken);
            AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.makeGuestJwtDetails(nickname));
            return new BaseResponse(
                    BaseResponseStatus.OK,
                    BaseResponseMessage.토큰_재발급이_성공했습니다.getMessage(),
                    new ReissueTokenResponse(authTokens)
            );
        }
        long userId = jwtProvider.getId(refreshToken);
        User user = getUser.getUserById(userId);
        AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.from(user));
        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.토큰_재발급이_성공했습니다.getMessage(),
                new ReissueTokenResponse(authTokens)
        );
    }
}
