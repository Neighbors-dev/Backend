package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.application.letter.dto.GetLetterDetailRequest;
import com.neighbors.tohero.application.letter.dto.UpdateLetterPublic;
import com.neighbors.tohero.application.letter.service.LetterService;
import com.neighbors.tohero.common.jwt.JwtUserDetails;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "편지 API", description = "편지를 생성하는 API입니다. content, isPublic은 필수 정보입니다. TargetJob, addressId, heroName은 사용자에게 입력받은 여부에 따라 json에 포함/미포함 할 수 있습니다. readingAlarm은 열람여부를 메시지로 받을지 여부이며, 로그인한 유저일 경우만 json에 포함시키면 됩니다. ")
    @PostMapping("")
    public ResponseEntity<BaseResponse> createLetter(
            @Parameter(hidden=true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @RequestBody @Validated CreateLetterRequest createLetterRequest
    ) {
        return ResponseEntity.ok()
                .body(letterService.createLetter(jwtUserDetail, createLetterRequest));
    }

    @Operation(summary = "편지 API", description = "편지를 상세 조회하는 API입니다.")
    @GetMapping("/detail")
    public ResponseEntity<BaseResponse> getLetterDetail(@ParameterObject GetLetterDetailRequest getLetterDetailRequest){
        return ResponseEntity.ok()
                .body(letterService.getLetterDetail(getLetterDetailRequest));
    }

    @Operation(summary = "편지 API", description = "내가 작성한 편지를 조회하는 API입니다. 로그인한 유저만 사용할 수 있습니다.")
    @GetMapping("")
    public ResponseEntity<BaseResponse> getMyLetters(@Parameter(hidden = true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail){
        return ResponseEntity.ok()
                .body(letterService.getMyLetters(jwtUserDetail.getUserId()));
    }

    @Operation(summary = "편지 API", description = "편지의 공개 여부를 수정하는 API입니다. 로그인한 유저만 사용할 수 있습니다.")
    @PutMapping("")
    public ResponseEntity<BaseResponse> updateLetterPublic(
            @Parameter(hidden = true) @AuthenticationPrincipal JwtUserDetails jwtUserDetail,
            @RequestBody @Validated UpdateLetterPublic updateLetterPublic
    ){
        return ResponseEntity.ok()
                .body(letterService.updateLetterPublic(jwtUserDetail.getUserId(), updateLetterPublic));
    }
}
