package com.neighbors.tohero.application.letter.dto;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;

import java.time.format.DateTimeFormatter;
import java.util.List;

public record GetMyLettersResponse (
    List<MyLetterInfo> myLetterInfos
){
    public record MyLetterInfo(
            long letterId,
            String to,
            String from,
            String createdAt,
            boolean isOpened,
            String content,
            boolean isPublic
    ){
        public static MyLetterInfo from(Letter letter){
            return new MyLetterInfo(
                    letter.getLetterId(),
                    letter.getTargetName(),
                    letter.getWriter(),
                    letter.getCreatedDate().format(DateTimeFormatter.ofPattern("YY.MM.dd")),
                    letter.isOpened(),
                    letter.getLetterContent(),
                    letter.isPublic()
            );
        }
    }

    public static GetMyLettersResponse from(List<Letter> myLetters){
        List<MyLetterInfo> myLetterInfos = myLetters.stream()
                .map(MyLetterInfo::from)
                .toList();
        return new GetMyLettersResponse(myLetterInfos);
    }
}
