package com.neighbors.tohero.domain.domain.news.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class News {
    private long newsId;
    private String title;
    private String content;

    public static News of(long newsId, String title, String content) {
        return new News(newsId, title, content);
    }
}
