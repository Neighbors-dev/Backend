package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.sharing.service.SharingService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sharing")
public class SharingController {

    private final SharingService sharingService;

    @Operation(summary = "공유하기 API", description = "공유하기 메시지보낼 때 사용할 추천인 코드를 생성해주는 API입니다.")
    @GetMapping("/recommenderCode")
    public ResponseEntity<BaseResponse> getRecommenderCode(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail
    ){
        return ResponseEntity.ok()
                .body(sharingService.getRecommenderCode(jwtUserDetail.getEmail()));
    }

    @Operation(summary = "공유하기 API", description = "공유하기 페이지 조회하는 API입니다.")
    @GetMapping("")
    public ResponseEntity<BaseResponse> getSharingPageInfo(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail
    ){
        return ResponseEntity.ok()
                .body(sharingService.getSharingPageInfo(jwtUserDetail.getUserId()));
    }
}
