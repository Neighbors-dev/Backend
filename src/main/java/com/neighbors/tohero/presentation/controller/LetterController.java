package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.application.letter.service.LetterService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class LetterController {

    private final LetterService letterService;

    @PostMapping("/letter")
    public ResponseEntity<BaseResponse> createLetter(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @RequestBody @Validated CreateLetterRequest createLetterRequest
    ) {
        return ResponseEntity.ok()
                .body(letterService.createLetter(jwtUserDetail, createLetterRequest));
    }
}
