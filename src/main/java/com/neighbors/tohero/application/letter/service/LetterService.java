package com.neighbors.tohero.application.letter.service;

import com.neighbors.tohero.application.baseResponse.BaseResponse;
import com.neighbors.tohero.application.letter.dto.CreateLetterRequest;
import com.neighbors.tohero.domain.domain.letter.service.CreateLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LetterService {

    private final CreateLetter createLetter;

    public BaseResponse createLetter(CreateLetterRequest createLetterRequest) {
        return null;
    }
}
