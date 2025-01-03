package com.neighbors.santa.application.notice.dto;

import com.neighbors.santa.domain.notice.model.Notice;

import java.time.LocalDateTime;
import java.util.List;

public record GetNoticeResponse(List<NoticeDTO> noticeList) {

    public record NoticeDTO(
            String title,
            String content,
            String createdAT
    ) {
        public static NoticeDTO from(Notice notice) {
            return new GetNoticeResponse.NoticeDTO(
                    notice.getNoticeTitle(),
                    notice.getNoticeContent(),
                    notice.getCreatedAt().toLocalDate().toString()
            );
        }
    }

    public static GetNoticeResponse createSuccessObjFrom(List<Notice> notices) {
        return new GetNoticeResponse(
                notices.stream()
                        .map(GetNoticeResponse.NoticeDTO::from)
                        .toList()
        );
    }
}
