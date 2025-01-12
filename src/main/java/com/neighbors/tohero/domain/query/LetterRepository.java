package com.neighbors.tohero.domain.query;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import com.neighbors.tohero.infrastructure.repository.LetterEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface LetterRepository {
    long getTotalLetterNumber();
    List<Letter> getLetters(Function<LetterEntityRepository, Optional<List<LetterEntity>>> function);
    Letter createLetter(Letter letter);
    void remainLetterWithoutUser(long userId);
    Letter getLetter(Function<LetterEntityRepository, Optional<LetterEntity>> function);
    void updateLetter(Consumer<LetterEntityRepository> consumer);
}
