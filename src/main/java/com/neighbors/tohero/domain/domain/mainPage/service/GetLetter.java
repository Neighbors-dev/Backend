package com.neighbors.tohero.domain.domain.mainPage.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.query.LetterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetLetter {

    private final LetterRepository letterRepository;

    public long getTotalLetterNumber(){
        return letterRepository.getTotalLetterNumber();
    }

    public List<Letter> getPageableLetter(Pageable pageable){
        return letterRepository.getPageableLetter(pageable);
    }

    public Letter getLetterById(long letterId){
        return null;
    }
}
