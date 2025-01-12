package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.application.letter.dto.GetLetterDetailRequest;
import com.neighbors.tohero.application.letter.dto.UpdateLetterPublic;
import com.neighbors.tohero.application.letter.service.LetterService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/letter")
public class LetterController {

    private final LetterService letterService;

    @PostMapping("")
    public ResponseEntity<BaseResponse> createLetter(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @RequestBody @Validated CreateLetterRequest createLetterRequest
    ) {
        return ResponseEntity.ok()
                .body(letterService.createLetter(jwtUserDetail, createLetterRequest));
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse> getLetterDetail(@ParameterObject GetLetterDetailRequest getLetterDetailRequest){
        return ResponseEntity.ok()
                .body(letterService.getLetterDetail(getLetterDetailRequest));
    }

    @GetMapping("")
    public ResponseEntity<BaseResponse> getMyLetters(@Parameter(hidden = true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail){
        return ResponseEntity.ok()
                .body(letterService.getMyLetters(jwtUserDetail.getUserId()));
    }

    @PutMapping("")
    public ResponseEntity<BaseResponse> updateLetterPublic(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @RequestBody @Validated UpdateLetterPublic updateLetterPublic
    ){
        return ResponseEntity.ok()
                .body(letterService.updateLetterPublic(jwtUserDetail.getUserId(), updateLetterPublic));
    }
}
