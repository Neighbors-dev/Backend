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

@Repository
@RequiredArgsConstructor
public class LetterRepositoryImpl implements LetterRepository {

    private final LetterEntityRepository letterEntityRepository;
    private final LetterMapper letterMapper;

    @Override
    public long getTotalLetterNumber() {
        return letterEntityRepository.count();
    }

    @Override
    public List<Letter> getPageableLetter(Pageable pageable) {
        List<LetterEntity> letterEntities =  letterEntityRepository.findPagedLetterEntity(pageable).getContent();

        return letterEntities.stream()
                .map(letterMapper::toDomain)
                .toList();
    }
}
