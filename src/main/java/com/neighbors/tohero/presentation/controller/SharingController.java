package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.sharing.service.SharingService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
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

    @GetMapping("/recommenderCode")
    public ResponseEntity<BaseResponse> getRecommenderCode(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail
    ){
        return ResponseEntity.ok()
                .body(sharingService.getRecommenderCode(jwtUserDetail.getEmail()));
    }

}
