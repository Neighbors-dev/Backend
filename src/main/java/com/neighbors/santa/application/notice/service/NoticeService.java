package com.neighbors.santa.application.notice.service;

import com.neighbors.santa.application.baseResponse.BaseResponse;
import com.neighbors.santa.application.baseResponse.BaseResponseMessage;
import com.neighbors.santa.application.baseResponse.BaseResponseStatus;
import com.neighbors.santa.application.notice.dto.GetNoticeResponse;
import com.neighbors.santa.domain.notice.model.Notice;
import com.neighbors.santa.domain.notice.service.GetNotice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final GetNotice getNotice;

    public BaseResponse<GetNoticeResponse> getNotice(Pageable pageable){

        List<Notice> notices = getNotice.getPagedNotice(pageable);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.공지_조회_성공했습니댜.getMessage(),
                GetNoticeResponse.createSuccessObjFrom(notices)
        );
    }

}
