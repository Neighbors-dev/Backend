package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.news.NewsException;
import com.neighbors.tohero.domain.domain.news.model.News;
import com.neighbors.tohero.domain.query.NewsRepository;
import com.neighbors.tohero.infrastructure.entity.NewsEntity;
import com.neighbors.tohero.infrastructure.mapper.NewsMapper;
import com.neighbors.tohero.infrastructure.repository.NewsEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class NewsRepositoryImpl implements NewsRepository {

    private final NewsEntityRepository newsEntityRepository;
    private final NewsMapper newsMapper;

    @Override
    public List<News> getNewsList(Function<NewsEntityRepository, Optional<List<NewsEntity>>> function) {
        List<NewsEntity> newsEntities = function.apply(newsEntityRepository)
                .orElseThrow(()-> new NewsException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.뉴스_조회가_실패했습니다.getMessage()
                ));

        return newsEntities.stream()
                .map(newsMapper::toDomain)
                .toList();
    }
}
