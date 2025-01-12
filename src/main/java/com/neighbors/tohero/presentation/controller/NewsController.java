package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.news.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("")
    public ResponseEntity<BaseResponse> getPagedNews(@ParameterObject Pageable pageable){
        return ResponseEntity.ok()
                .body(newsService.getPagedNews(pageable));
    }
}
