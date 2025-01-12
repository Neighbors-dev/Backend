package com.neighbors.tohero.domain.domain.news.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.news.model.News;
import com.neighbors.tohero.domain.query.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetNews {

    private final NewsRepository newsRepository;

    public List<News> getPagedNews(Pageable pageable){
        return newsRepository.getNewsList(repo -> repo.findAllByPagable(pageable));
    }
}
