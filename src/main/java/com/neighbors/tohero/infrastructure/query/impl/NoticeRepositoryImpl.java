package com.neighbors.tohero.infrastructure.query.impl;

import com.neighbors.tohero.application.baseResponse.BaseResponseMessage;
import com.neighbors.tohero.application.baseResponse.BaseResponseStatus;
import com.neighbors.tohero.common.exception.notice.NoticeException;
import com.neighbors.tohero.domain.domain.notice.model.Notice;
import com.neighbors.tohero.domain.query.NoticeRepository;
import com.neighbors.tohero.infrastructure.entity.NoticeEntity;
import com.neighbors.tohero.infrastructure.mapper.NoticeMapper;
import com.neighbors.tohero.infrastructure.repository.NoticeEntityRepository;
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

    @Override
    public List<Notice> getTopNotices(int exposeNoticeNumber) {
        List<NoticeEntity> topNotices = noticeEntityRepository.findTopNoticeEntity(exposeNoticeNumber);

        return topNotices.stream()
                .map(noticeMapper::toDomain)
                .toList();
    }

    @Override
    public Notice getNoticeById(long noticeId) {
        NoticeEntity noticeEntity = noticeEntityRepository.findById(noticeId)
                .orElseThrow(() -> new NoticeException(
                        BaseResponseStatus.NO_RESULT,
                        BaseResponseMessage.일치하는_공지가_없습니다.getMessage()
                ));

        return noticeMapper.toDomain(noticeEntity);
    }
}
