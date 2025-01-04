package com.neighbors.tohero.application.mainPage.dto;

import com.neighbors.tohero.domain.mainPage.model.Letter;
import com.neighbors.tohero.domain.notice.model.Notice;

import java.util.List;

public record GetMainPageInfoResponse(
        List<TopNotices> topNotices,
        int writtenLetterNumber,
        List<OpenedLetter> openedLetters
) {
    public record TopNotices(
            String title
    ){
        public static TopNotices from(Notice notice){
            return new TopNotices(notice.getNoticeTitle());
        }
    }

    public record OpenedLetter(
            String to,
            String from,
            String content
    ){
        public static OpenedLetter from(Letter letter){
            return new OpenedLetter(letter.getTargetName(), letter.getFromUserName(), letter.getLetterContent());
        }
    }
}
