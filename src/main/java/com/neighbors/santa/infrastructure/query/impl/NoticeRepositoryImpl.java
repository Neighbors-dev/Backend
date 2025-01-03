package com.neighbors.santa.infrastructure.query.impl;

import com.neighbors.santa.domain.notice.model.Notice;
import com.neighbors.santa.domain.query.NoticeRepository;
import com.neighbors.santa.infrastructure.entity.NoticeEntity;
import com.neighbors.santa.infrastructure.mapper.NoticeMapper;
import com.neighbors.santa.infrastructure.repository.NoticeEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoticeRepositoryImpl implements NoticeRepository {

    private final NoticeEntityRepository noticeEntityRepository;
    private final NoticeMapper noticeMapper;

    @Override
    public List<Notice> getPagedNotice(Pageable pageable) {
        List<NoticeEntity> noticeEntities = noticeEntityRepository.findPagedNoticeEntity(pageable).getContent();

        return noticeEntities.stream()
                .map(noticeMapper::toDomain)
                .toList();
    }
}
