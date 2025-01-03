package com.neighbors.tohero.infrastructure.mapper;

import com.neighbors.tohero.domain.notice.model.Notice;
import com.neighbors.tohero.infrastructure.entity.NoticeEntity;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {

    public Notice toDomain(NoticeEntity noticeEntity) {
        return Notice.of(noticeEntity.getNoticeId(), noticeEntity.getNoticeTitle(), noticeEntity.getNoticeContent(), noticeEntity.getCreated_at());
    }
}
