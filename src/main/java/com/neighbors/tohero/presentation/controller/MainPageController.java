package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.mainPage.service.MainPageService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final MainPageService mainPageService;

    @Operation(summary = "메인 페이지 API", description = "메인 페이지에서 사용되는 API입니다. 최상단 공지 1건, 작성된 전체 편지 개수, 공개된 편지 무한 페이징 정보가 포함됩니다.")
    @GetMapping("/mainPage")
    public ResponseEntity<BaseResponse> getMainPageInfo(@ParameterObject Pageable pageable){
        return ResponseEntity.ok()
                .body(mainPageService.getMainPageInfo(pageable));
    }

    @Operation(summary = "메인 페이지 API", description = "메인 페이지에서 사용되는 API입니다. 아래로 스크롤 할 때 공개된 편지 무한 페이징 하는 API 입니다.")
    @GetMapping("/mainPage/letter")
    public ResponseEntity<BaseResponse> getMainPageLetterInfo(@ParameterObject Pageable pageable){
        return ResponseEntity.ok()
                .body(mainPageService.getMainPageLetterInfo(pageable));
    }
}
