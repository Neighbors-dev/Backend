package com.neighbors.tohero.application.letter.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.application.letter.dto.CreateLetterResponse;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.exception.address.AddressException;
import com.neighbors.tohero.common.exception.letter.LetterException;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.address.service.GetAddress;
import com.neighbors.tohero.domain.domain.letter.service.CreateLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final CreateLetter createLetter;
    private final GetAddress getAddress;

    public BaseResponse<CreateLetterResponse> createLetter(final JwtUserDetails jwtUserDetail, final CreateLetterRequest createLetterRequest) {

        throwExceptionIfAddressIsNotExist(createLetterRequest.addressId());

        if(jwtUserDetail.getRole() == Role.GUEST){
            return createGuestLetter(jwtUserDetail.getNickname(), createLetterRequest);
        }
        long createdLetterId = createLetter.createLetter(
                jwtUserDetail.getUserId(),
                jwtUserDetail.getNickname(),
                createLetterRequest
        );

        throwIfLetterNotCreate(createdLetterId);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.편지가_성공적으로_생성_되었습니다.getMessage(),
                new CreateLetterResponse(createdLetterId)
        );
    }

    private BaseResponse<CreateLetterResponse> createGuestLetter(final String nickname, final CreateLetterRequest createLetterRequest) {
        long createdLetterId = createLetter.createGuestLetter(
                nickname,
                createLetterRequest.content(),
                createLetterRequest.targetJob(),
                createLetterRequest.addressId(),
                createLetterRequest.heroName(),
                createLetterRequest.isPublic()
        );

        throwIfLetterNotCreate(createdLetterId);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.편지가_성공적으로_생성_되었습니다.getMessage(),
                new CreateLetterResponse(createdLetterId)
        );
    }

    private void throwExceptionIfAddressIsNotExist(final Long addressId){
        if(addressId == null) {return ;}
        if(!getAddress.existAddressById(addressId)){
            throw new AddressException(
                    BaseResponseStatus.NO_RESULT,
                    BaseResponseMessage.일치하는_관할서_정보가_없습니다.getMessage()
            );
        }
    }

    private void throwIfLetterNotCreate(final long createdLetterId){
        if(createdLetterId == 0){
            throw new LetterException(
                    BaseResponseStatus.BAD_REQUEST,
                    BaseResponseMessage.편지_생성이_실패_했습니다.getMessage()
            );
        }
    }
}
