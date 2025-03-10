package com.neighbors.tohero.application.letter.dto;

import com.neighbors.tohero.domain.domain.mainPage.model.Letter;

import java.time.LocalDate;

public record GetLetterDetailResponse(
        LetterInfo letterInfo
) {
    public record LetterInfo(
        long letterId,
        String content,
        String from,
        String to,
        boolean isOpened,
        boolean isPublic,
        LocalDate createdAt
    ){}

    public static GetLetterDetailResponse from(Letter letter) {
        return new GetLetterDetailResponse(new LetterInfo(letter.getLetterId(), letter.getLetterContent(), letter.getWriter(), letter.getTargetName(), letter.isOpened(), letter.isPublic(), letter.getCreatedDate().toLocalDate()));
    }
}
