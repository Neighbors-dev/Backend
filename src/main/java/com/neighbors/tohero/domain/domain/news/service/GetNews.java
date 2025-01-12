package com.neighbors.tohero.domain.domain.news.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.news.model.News;
import lombok.RequiredArgsConstructor;

import java.awt.print.Pageable;
import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetNews {

    public List<News> getPagedNews(Pageable pageable){
        return null;
    }
}
