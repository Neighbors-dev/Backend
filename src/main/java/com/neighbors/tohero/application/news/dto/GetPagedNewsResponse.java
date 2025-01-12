package com.neighbors.tohero.application.news.dto;

import com.neighbors.tohero.domain.domain.news.model.News;

import java.util.List;

public record GetPagedNewsResponse(
        List<NewsInfo> newsInfos
) {
    public record NewsInfo(
            long newsId,
            String title,
            String content
    ){
        public static NewsInfo from(News news){
            return new NewsInfo(news.getNewsId(), news.getTitle(), news.getContent());
        }
    }

    public static GetPagedNewsResponse from(List<News> newsInfos) {
        List<NewsInfo> newsInfoList = newsInfos.stream()
                .map(NewsInfo::from)
                .toList();
        return new GetPagedNewsResponse(newsInfoList);
    }
}
