package com.neighbors.tohero.application.news.dto;

import java.util.List;

public record GetPagedNewsResponse(
        List<NewsInfo> newsInfos
) {
    public record NewsInfo(
            long newsId,
            String title,
            String content
    ){
    }
}
