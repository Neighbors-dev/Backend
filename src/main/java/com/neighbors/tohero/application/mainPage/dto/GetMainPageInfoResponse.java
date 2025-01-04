package com.neighbors.tohero.application.mainPage.dto;

import java.util.List;

public record GetMainPageInfoResponse(
        List<TopNotices> topNotices,
        int writtenLetterNumber
) {
    public record TopNotices(
            String title
    ){}
}
