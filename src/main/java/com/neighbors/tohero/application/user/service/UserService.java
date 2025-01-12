package com.neighbors.tohero.application.user.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.user.dto.AuthenticateUserRequest;
import com.neighbors.tohero.application.user.dto.AuthenticateUserResponse;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.jwt.AuthTokens;
import com.neighbors.tohero.common.jwt.JwtProvider;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.domain.domain.user.service.CreateUser;
import com.neighbors.tohero.domain.domain.user.service.DeleteUser;
import com.neighbors.tohero.domain.domain.user.service.UpdateUser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UpdateUser updateUser;
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final JwtProvider jwtProvider;

    public BaseResponse updateUserName(long userId, String nickname){

        updateUser.updateUserName(userId, nickname);
        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.유저_이름_변경이_완료되었습니다.getMessage()
        );
    }

    public BaseResponse<AuthenticateUserResponse> authenticateUser(AuthenticateUserRequest authenticateUserRequest){
        if(authenticateUserRequest.role() == Role.USER){
            return returnLoginedUserToken(authenticateUserRequest);
        }
        return returnGuestUserToken(authenticateUserRequest);
    }

    public BaseResponse logout(HttpSession httpSession){
        //todo : Redis record 삭제
        httpSession.invalidate();
        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.로그아웃이_성공적으로_실행되었습니다.getMessage()
        );
    }

    public BaseResponse signout(JwtUserDetails jwtUserDetails, HttpSession httpSession){
        httpSession.invalidate();

        deleteUser.signout(jwtUserDetails.getUserId());

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.성공적으로_탈퇴했습니다.getMessage()
        );
    }

    private BaseResponse<AuthenticateUserResponse> returnLoginedUserToken(AuthenticateUserRequest authenticateUserRequest) {
        User createdUser = createUser.createUser(User.toEntity(authenticateUserRequest));
        AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.from(createdUser));

        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.유저가_성공적으로_인증되었습니다.getMessage(),
                AuthenticateUserResponse.toUserDTO(authTokens, createdUser)
        );
    }

    private BaseResponse<AuthenticateUserResponse> returnGuestUserToken(AuthenticateUserRequest authenticateUserRequest) {
        AuthTokens authTokens = jwtProvider.createToken(JwtUserDetails.makeGuestJwtDetails(authenticateUserRequest.nickname()));
        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.GUEST_유저_토큰이_정상적으로_생성되었습니다.getMessage(),
                AuthenticateUserResponse.toGuestDTO(authTokens)
        );
    }
}
