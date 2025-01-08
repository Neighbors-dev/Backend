package com.neighbors.tohero.domain.domain.notice.service;

import com.neighbors.tohero.common.annotaion.DomainService;
import com.neighbors.tohero.domain.domain.notice.model.Notice;
import com.neighbors.tohero.domain.query.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

@DomainService
@RequiredArgsConstructor
public class GetNotice {

    private final NoticeRepository noticeRepository;

    public List<Notice> getPagedNotice(Pageable pageable) {
        return noticeRepository.getPagedNotice(pageable);
    }

    public List<Notice> getTopNotices(int exposeNoticeNumber){
        return noticeRepository.getTopNotices(exposeNoticeNumber);
    }

    public Notice getNotice(long noticeId) {
        return noticeRepository.getNoticeById(noticeId);
    }
}
