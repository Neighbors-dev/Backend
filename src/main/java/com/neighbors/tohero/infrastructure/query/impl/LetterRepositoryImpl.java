package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.query.LetterRepository;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import com.neighbors.tohero.infrastructure.mapper.LetterMapper;
import com.neighbors.tohero.infrastructure.repository.LetterEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class LetterRepositoryImpl implements LetterRepository {

    private final LetterEntityRepository letterEntityRepository;
    private final LetterMapper letterMapper;

    @Override
    public long getTotalLetterNumber() {
        return letterEntityRepository.countPublicLetter();
    }

    @Override
    public List<Letter> getPageableLetter(Pageable pageable) {
        List<LetterEntity> letterEntities =  letterEntityRepository.findPagedLetterEntity(pageable).getContent();

        return letterEntities.stream()
                .map(letterMapper::toDomain)
                .toList();
    }

    @Override
    public Letter createLetter(Letter letter) {
        LetterEntity newLetterEntity = letterMapper.toEntity(letter);
        LetterEntity createdLetterEntity =  letterEntityRepository.save(newLetterEntity);
        return letterMapper.toDomain(createdLetterEntity);
    }

    @Override
    public void remainLetterWithoutUser(long userId) {
        letterEntityRepository.findAllByUserId(userId)
                .forEach(letter -> {
                    letter.remainLetterWithoutUser();
                    letterEntityRepository.save(letter);
                });
    }

    @Override
    public Letter getLetter(Function<LetterRepository, Optional<Letter>> function) {
        return null;
    }
}
