package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import org.springframework.stereotype.Component;

@Component
public class LetterMapper {
    public Letter toDomain(LetterEntity letterEntity) {
        return Letter.of(
                letterEntity.getLetterId(),
                letterEntity.getLetterContent(),
                letterEntity.isOpened(),
                letterEntity.getFromUserName(),
                letterEntity.getTargetName()
        );
    }
}
