package com.neighbors.tohero.application.news.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.news.dto.GetPagedNewsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class NewsService {

    public BaseResponse<GetPagedNewsResponse> getPagedNews(Pageable pageable){
        return null;
    }
}
