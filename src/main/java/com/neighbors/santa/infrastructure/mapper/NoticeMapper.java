package com.neighbors.santa.infrastructure.mapper;

import com.neighbors.santa.domain.notice.model.Notice;
import com.neighbors.santa.infrastructure.entity.NoticeEntity;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {

    public Notice toDomain(NoticeEntity noticeEntity) {
        return Notice.of(noticeEntity.getNoticeId(), noticeEntity.getNoticeTitle(), noticeEntity.getNoticeContent(), noticeEntity.getCreated_at());
    }
}
