package com.neighbors.tohero.domain.domain.letter.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.query.LetterRepository;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class DeleteLetter {

    private final LetterRepository letterRepository;

    public void deleteLetterByUserIdAndLetterId(long userId, long letterId) {
        letterRepository.deleteLetter(repo -> repo.deleteByUserIdAndLetterId(userId, letterId));
    }
}
