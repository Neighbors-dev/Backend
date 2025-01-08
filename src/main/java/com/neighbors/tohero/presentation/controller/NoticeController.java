package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지 API", description = "공지사항 무한페이징 API입니다.")
    @GetMapping("/notice")
    public ResponseEntity<BaseResponse> getNotice(@ParameterObject Pageable pageable){
        return ResponseEntity.ok()
                .body(noticeService.getNotice(pageable));
    }

    @Operation(summary = "공지 API", description = "공지사항 상세보기 API입니다.")
    @GetMapping("/notice/detail")
    public ResponseEntity<BaseResponse> getNoticeDetail(@RequestParam long noticeId){
        return ResponseEntity.ok()
                .body(noticeService.getNoticeDetail(noticeId));
    }
}
