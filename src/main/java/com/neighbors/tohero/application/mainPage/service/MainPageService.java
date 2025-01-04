package com.neighbors.tohero.application.mainPage.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.mainPage.dto.GetMainPageInfoResponse;
import com.neighbors.tohero.domain.notice.service.GetNotice;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MainPageService {

    private final GetNotice getNotice;

    public BaseResponse<GetMainPageInfoResponse> getMainPageInfo(Pageable pageable){
        return null;
    }
}
