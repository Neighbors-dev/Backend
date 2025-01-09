package com.neighbors.tohero.domain.domain.letter.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.common.enums.TargetJob;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class CreateLetter {
    public long createLetter(String writer, String content, TargetJob targetJob, Long addressId, String heroName){
        return 1L;
    }
}
