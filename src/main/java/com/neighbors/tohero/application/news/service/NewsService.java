package com.neighbors.tohero.application.news.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.application.news.dto.GetPagedNewsResponse;
import com.neighbors.tohero.domain.domain.news.model.News;
import com.neighbors.tohero.domain.domain.news.service.GetNews;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final GetNews getNews;

    public BaseResponse<GetPagedNewsResponse> getPagedNews(Pageable pageable){
        List<News> news = getNews.getPagedNews(pageable);

        return new BaseResponse<>(
                BaseResponseStatus.OK,
                BaseResponseMessage.뉴스_조회가_성공했습니다.getMessage(),
                GetPagedNewsResponse.from(news)
        );
    }
}
