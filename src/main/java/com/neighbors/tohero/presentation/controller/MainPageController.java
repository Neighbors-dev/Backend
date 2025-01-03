package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class MainPageController {

    private final MainPageService mainPageService;

    @Operation(summary = "메인페이지 API", description = "메인 페이지를 위한 API 입니다. 최상단 공지, 전체 편지 개수, 보여질 메시지 목록이 포함됩니다.")
    @GetMapping("/main")
    public ResponseEntity<BaseResponse> mainPage(@ParameterObject Pageable pageable){
        return ResponseEntity.ok()
                .body(mainPageService.mainPage(pageable));
    }
}
