package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LetterRepository {
    int getTotalLetterNumber();
    List<Letter> getPageableLetter(Pageable pageable);
}
