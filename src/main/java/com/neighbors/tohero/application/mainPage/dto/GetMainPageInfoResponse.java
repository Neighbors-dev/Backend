package com.neighbors.tohero.application.mainPage.dto;

import com.neighbors.tohero.domain.domain.notice.model.Notice;

import java.util.List;

public record GetMainPageInfoResponse(
        List<TopNotices> topNotices,
        long writtenLetterNumber,
        List<OpenedLetter> openedLetters
) {
    public record TopNotices(
            String title
    ){
        public static TopNotices from(Notice notice){
            return new TopNotices(notice.getNoticeTitle());
        }
    }
}
