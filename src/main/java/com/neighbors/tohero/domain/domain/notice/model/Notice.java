package com.neighbors.tohero.domain.domain.notice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Notice {
    private long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private LocalDateTime createdAt;

    public static Notice of(long noticeId, String noticeTitle, String noticeContent, LocalDateTime createdAt) {
        return new Notice(noticeId, noticeTitle, noticeContent, createdAt);
    }
}
