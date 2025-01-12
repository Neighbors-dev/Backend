package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.news.model.News;
import com.neighbors.tohero.infrastructure.entity.NewsEntity;
import com.neighbors.tohero.infrastructure.repository.NewsEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface NewsRepository {
    public List<News> getNewsList(Function<NewsEntityRepository, Optional<List<NewsEntity>>> function);
}
