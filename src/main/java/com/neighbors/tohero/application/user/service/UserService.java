package com.neighbors.tohero.application.user.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.domain.user.service.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UpdateUser updateUser;

    public BaseResponse updateUserName(long userId, String nickname){

        updateUser.updateUserName(userId, nickname);

        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.유저_이름_변경이_완료되었습니다.getMessage()
        );
    }
}
