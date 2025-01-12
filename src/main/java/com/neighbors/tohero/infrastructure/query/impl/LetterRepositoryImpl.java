package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.letter.LetterException;
import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.query.LetterRepository;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import com.neighbors.tohero.infrastructure.mapper.LetterMapper;
import com.neighbors.tohero.infrastructure.repository.LetterEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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
    public List<Letter> getLetters(Function<LetterEntityRepository, Optional<List<LetterEntity>>> function) {
        List<LetterEntity> letterEntities =  function.apply(letterEntityRepository)
                .orElseThrow(()-> new LetterException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.일치하는_편지가_없습니다.getMessage()
                ));

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
                .orElse(new ArrayList<>())
                .forEach(letter -> {
                    letter.remainLetterWithoutUser();
                    letterEntityRepository.save(letter);
                });
    }

    @Override
    public Letter getLetter(Function<LetterEntityRepository, Optional<LetterEntity>> function) {
        LetterEntity letterEntity = function.apply(letterEntityRepository)
                .orElseThrow(()-> new LetterException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.일치하는_편지가_없습니다.getMessage()
                ));

        return letterMapper.toDomain(letterEntity);
    }

    @Override
    public void updateLetter(Consumer<LetterEntityRepository> consumer) {
        consumer.accept(letterEntityRepository);
    }

    @Override
    public void deleteLetter(Consumer<LetterEntityRepository> consumer) {
        consumer.accept(letterEntityRepository);
    }
}
