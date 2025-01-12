package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.news.model.News;
import com.neighbors.tohero.infrastructure.entity.NewsEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsMapper {

    public News toDomain(NewsEntity newsEntity) {
        return News.of(newsEntity.getNewsId(), newsEntity.getNewsTitle(), newsEntity.getNewsContent());
    }
}
