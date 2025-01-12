package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetterMapper {

    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public Letter toDomain(LetterEntity letterEntity) {
        if(letterEntity.getUser() == null && letterEntity.getAddress() == null){
            return Letter.createNonUserAndAddress(letterEntity);
        }
        if(letterEntity.getUser() == null){
            return Letter.createNonUser(letterEntity, addressMapper.toDomain(letterEntity.getAddress()));
        }
        if(letterEntity.getAddress() == null){
            return Letter.createNonAddress(letterEntity, userMapper.toDomain(letterEntity.getUser()));
        }
        return Letter.from(letterEntity, addressMapper.toDomain(letterEntity.getAddress()), userMapper.toDomain(letterEntity.getUser()));
    }

    public LetterEntity toEntity(Letter letter) {
        return LetterEntity.builder()
                .letterContent(letter.getLetterContent())
                .isOpened(false)
                .targetName(letter.getTargetName())
                .writer(letter.getWriter())
                .address(addressMapper.toEntity(letter.getAddress()))
                .user(userMapper.toEntity(letter.getUser()))
                .isPublic(letter.isPublic())
                .readingAlarm(letter.isReadingAlarm())
                .build();
    }
}
