package com.neighbors.tohero.domain.domain.mainPage.model;

import com.neighbors.tohero.domain.domain.address.model.Address;
import com.neighbors.tohero.domain.domain.user.model.User;
import com.neighbors.tohero.infrastructure.entity.LetterEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Letter {
    private long letterId;
    private String letterContent;
    private boolean isOpened;
    private String targetName;
    private boolean isPublic;
    private boolean readingAlarm;

    private Address address;

    private String writer;
    private User user;

    private LocalDateTime createdDate;

    public String getTargetName(){
        if(targetName == null) {
            return "익명의";
        }
        return targetName;
    }

    public static Letter createNonUserAndAddress(LetterEntity letterEntity) {
        return new Letter(
                letterEntity.getLetterId(),
                letterEntity.getLetterContent(),
                letterEntity.isOpened(),
                letterEntity.getTargetName(),
                letterEntity.getIsPublic(),
                letterEntity.getReadingAlarm(),
                null,letterEntity.getWriter(),null,
                letterEntity.created_at
        );
    }

    public static Letter createNonUser(LetterEntity letterEntity, Address address) {
        return new Letter(
                letterEntity.getLetterId(),
                letterEntity.getLetterContent(),
                letterEntity.isOpened(),
                letterEntity.getTargetName(),
                letterEntity.getIsPublic(),
                letterEntity.getReadingAlarm(),
                address
                ,letterEntity.getWriter(),
                null,
                letterEntity.created_at
        );
    }

    public static Letter createNonAddress(LetterEntity letterEntity, User user) {
        return new Letter(
                letterEntity.getLetterId(),
                letterEntity.getLetterContent(),
                letterEntity.isOpened(),
                letterEntity.getTargetName(),
                letterEntity.getIsPublic(),
                letterEntity.getReadingAlarm(),
                null,
                letterEntity.getUser().getNickName(),
                user,
                letterEntity.created_at
        );
    }

    public static Letter from(LetterEntity letterEntity, Address address, User user) {
        return new Letter(
                letterEntity.getLetterId(),
                letterEntity.getLetterContent(),
                letterEntity.isOpened(),
                letterEntity.getTargetName(),
                letterEntity.getIsPublic(),
                letterEntity.getReadingAlarm(),
                address,
                letterEntity.getUser().getNickName(),
                user,
                letterEntity.created_at
        );
    }
}
