package com.neighbors.tohero.application.mainPage.dto;

import java.util.List;

public record GetMainPageInfoResponse(
        List<TopNotices> topNotices,
        int writtenLetterNumber,
        List<OpenedLetter> openedLetters
) {
    public record TopNotices(
            String title
    ){}

    public record OpenedLetter(
            String to,
            String from,
            String content
    ){}
}
