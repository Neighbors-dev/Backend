package com.neighbors.tohero.domain.domain.letter.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.query.LetterRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UpdateLetter {

    private final LetterRepository letterRepository;

    public void updateLetterPublic(long userId, long letterId, boolean isPublic){
        letterRepository.updateLetter(repo -> repo.updateLetterPublic(userId, letterId, isPublic));
    }
}
