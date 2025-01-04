package com.neighbors.tohero.application.mainPage.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.mainPage.dto.GetMainPageInfoResponse;
import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.domain.mainPage.service.GetLetter;
import com.neighbors.tohero.domain.domain.notice.model.Notice;
import com.neighbors.tohero.domain.domain.notice.service.GetNotice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MainPageService {

    private final GetNotice getNotice;
    private final GetLetter getLetter;

    private static final int EXPOSED_NOTICE_NUMBER = 5;

    public BaseResponse<GetMainPageInfoResponse> getMainPageInfo(Pageable pageable){
        List<GetMainPageInfoResponse.TopNotices> topNotices = getTopNoticeFromDomain();
        long writtenLetterNumber = getLetter.getTotalLetterNumber();
        List<GetMainPageInfoResponse.OpenedLetter> openedLetters = getLetterFromDomain(pageable);

        return new BaseResponse(
                BaseResponseStatus.OK,
                BaseResponseMessage.메인페이지_조회가_정상적으로_실행되었습니다.getMessage(),
                new GetMainPageInfoResponse(topNotices, writtenLetterNumber, openedLetters)
        );
    }

    public BaseResponse getMainPageLetterInfo(Pageable pageable){
        return null;
    }

    private List<GetMainPageInfoResponse.TopNotices> getTopNoticeFromDomain(){
        List<Notice> topNotices = getNotice.getTopNotices(EXPOSED_NOTICE_NUMBER);

        return topNotices.stream()
                .map(GetMainPageInfoResponse.TopNotices::from)
                .toList();
    }

    private List<GetMainPageInfoResponse.OpenedLetter> getLetterFromDomain(Pageable pageable){
        List<Letter> openedLetters = getLetter.getPageableLetter(pageable);

        return openedLetters.stream()
                .map(GetMainPageInfoResponse.OpenedLetter::from)
                .toList();
    }
}
