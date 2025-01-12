package com.neighbors.tohero.application.letter.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.letter.dto.*;
import com.neighbors.tohero.common.enums.Role;
import com.neighbors.tohero.common.exception.address.AddressException;
import com.neighbors.tohero.common.exception.letter.LetterException;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import com.neighbors.tohero.domain.domain.address.service.GetAddress;
import com.neighbors.tohero.domain.domain.letter.service.CreateLetter;
import com.neighbors.tohero.domain.domain.letter.service.UpdateLetter;
import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.domain.mainPage.service.GetLetter;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final CreateLetter createLetter;
    private final GetLetter getLetter;
    private final GetAddress getAddress;
    private final UpdateLetter updateLetter;

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

    public BaseResponse<GetLetterDetailResponse> getLetterDetail(GetLetterDetailRequest getLetterDetailRequest){
        Letter matchedLetter = getLetter.getLetterById(getLetterDetailRequest.letterId());

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.편지가_성공적으로_조회되었습니다.getMessage(),
                GetLetterDetailResponse.from(matchedLetter)
        );
    }

    public BaseResponse<GetMyLettersResponse> getMyLetters(long userId){
        List<Letter> myLetters = getLetter.getMyLetters(userId);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.편지가_성공적으로_조회되었습니다.getMessage(),
                GetMyLettersResponse.from(myLetters)
        );
    }

    @Transactional
    public BaseResponse<UpdateLetterPublicResponse> updateLetterPublic(long userId, UpdateLetterPublic updateLetterPublic){
        updateLetter.updateLetterPublic(userId, updateLetterPublic.letterId(), updateLetterPublic.isPublic());

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.편지_공개_여부가_수정되었습니다.getMessage(),
                UpdateLetterPublicResponse.of(updateLetterPublic.isPublic())
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
