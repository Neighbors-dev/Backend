package com.neighbors.tohero.application.notice.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.notice.dto.GetNoticeResponse;
import com.neighbors.tohero.domain.domain.notice.model.Notice;
import com.neighbors.tohero.domain.domain.notice.service.GetNotice;
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
