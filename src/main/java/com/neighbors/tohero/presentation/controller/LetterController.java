package com.neighbors.tohero.presentation.controller;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.application.letter.service.LetterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse> createLetter(@RequestBody @Validated CreateLetterRequest createLetterRequest) {
        return ResponseEntity.ok()
                .body(letterService.createLetter(createLetterRequest));
    }
}
