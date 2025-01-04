package com.neighbors.tohero.domain.mainPage.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.mainPage.model.Letter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetLetter {

    public int getTotalLetterNumber(){
        return 0;
    }

    public List<Letter> getPageableLetter(Pageable pageable){

        return null;
    }
}
