package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LetterRepository {
    long getTotalLetterNumber();
    List<Letter> getPageableLetter(Pageable pageable);
    Letter createLetter(Letter letter);
    void remainLetterWithoutUser(long userId);
}
