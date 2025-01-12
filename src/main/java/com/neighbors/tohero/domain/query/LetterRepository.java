package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import com.neighbors.tohero.infrastructure.repository.LetterEntityRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface LetterRepository {
    long getTotalLetterNumber();
    List<Letter> getPageableLetter(Pageable pageable);
    Letter createLetter(Letter letter);
    void remainLetterWithoutUser(long userId);
    Letter getLetter(Function<LetterEntityRepository, Optional<LetterEntity>> function);
}
